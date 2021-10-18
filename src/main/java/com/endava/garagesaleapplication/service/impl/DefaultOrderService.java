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

    @Override
    public Order placeOrder(Order order) {
        orderDetailsValidationsExecutor(order);

        List<Asset> assetList = assetService.findOrderedAssetsInDb(order);
        OrderValidation.checkOneItemPerCategoryCondition(assetList);

        setFieldsToOrder(order, assetList);
        setOrdersFields(order);

        saveRequestInDb(order);
        updateDb(order);

        return order;
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAllAsList();
    }

    private double getTotalOrderPrice(Order order) {
        return order.getAssetList()
                .stream()
                .mapToDouble(asset -> this.assetService.getAsset(asset.getId()).getPrice())
                .sum();
    }

    private void orderDetailsValidationsExecutor(Order order) {
        EmailValidation.checkEmailValidity(order.getEmail());
        CardValidation.cardNumberValidation(order.getCard().getCardNumber());
    }

    private void setFieldsToOrder(Order order, List<Asset> assetList) {
        order.setAssetList(assetList);
        order.setTotalPrice(getTotalOrderPrice(order));
        order.setOrderDateTime(LocalDateTime.now());
    }

    private void setOrdersFields(Order order) {
        order.getCard().setOrder(order);
        order.getAssetList().forEach(asset -> asset.addToOrderList(order));
    }

    private void saveRequestInDb(Order order) {
        this.orderRepository.save(order);
        this.cardRepository.save(order.getCard());
    }

    private void updateDb(Order order) {
        this.assetService.decrementAssets(order.getAssetList());
    }
}