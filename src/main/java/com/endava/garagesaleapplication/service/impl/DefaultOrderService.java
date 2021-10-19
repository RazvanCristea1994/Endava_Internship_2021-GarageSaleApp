package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.repository.database.AssetRepository;
import com.endava.garagesaleapplication.repository.database.CardRepository;
import com.endava.garagesaleapplication.repository.database.OrderRepository;
import com.endava.garagesaleapplication.service.AssetService;
import com.endava.garagesaleapplication.service.OrderService;
import com.endava.garagesaleapplication.validator.CardValidation;
import com.endava.garagesaleapplication.validator.EmailValidation;
import com.endava.garagesaleapplication.validator.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("orderService")
public class DefaultOrderService implements OrderService {

    @Autowired
    private AssetService assetService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CardRepository cardRepository;

    /**
     * This method structures place order steps
     *
     * @param order input from the client
     * @return - {@link Order} the saved order with attributes as in the database
     */
    @Override
    public Order placeOrder(Order order) {
        orderDetailsValidationsExecutor(order);

        List<Asset> assetList = assetService.findOrderedAssetsInDb(order);
        OrderValidation.checkOneItemPerCategoryCondition(assetList);

        setFieldsToOrder(order, assetList);
        setOrdersFields(order);

        saveRequestInDb(order);
        this.assetService.decrementAssetsByOne(order.getAssetList());

        return order;
    }

    /**
     * This method shows the order history
     *
     * @return A list of orders {@link Order}
     */
    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAllAsList();
    }

    /**
     * This method sums all the product prices in an order
     *
     * @param order sent by the client
     * @return the total price of the order
     */
    private double getTotalOrderPrice(Order order) {
        return order.getAssetList()
                .stream()
                .mapToDouble(asset -> this.assetService.findById(asset.getId()).getPrice())
                .sum();
    }

    /**
     * This method executes the validation on order attributes
     *
     * @param order send by the client
     */
    private void orderDetailsValidationsExecutor(Order order) {
        EmailValidation.checkEmailValidity(order.getEmail());
        CardValidation.cardNumberValidation(order.getCard().getCardNumber());
    }

    /**
     * This method sets to order the fields that are not requested to the client and
     * field indicated by the client and previously taken from the database
     * <p>
     * The products that are set should be previously taken from DB (this will provide the foreign key later on when saved)
     *
     * @param order     sent by the client
     * @param assetList the list of products from db are set to the order
     */
    private void setFieldsToOrder(Order order, List<Asset> assetList) {
        order.setAssetList(assetList);
        order.setTotalPrice(getTotalOrderPrice(order));
        order.setOrderDateTime(LocalDateTime.now());
    }

    /**
     * This method sets order as an attribute to its own attributes
     * This will provide to the attributes the foreign key later on when saved in database
     *
     * @param order set by the client (some additional fields might be already set before this method)
     */
    private void setOrdersFields(Order order) {
        order.getCard().setOrder(order);
        order.getAssetList().forEach(asset -> asset.addToOrderList(order));
    }

    /**
     * This method saves in database the order and its fields (those that represent a table in DB)
     *
     * @param order order sent by client with additional fields set in the service
     */
    private void saveRequestInDb(Order order) {
        this.orderRepository.save(order);
        this.cardRepository.save(order.getCard());
    }
}