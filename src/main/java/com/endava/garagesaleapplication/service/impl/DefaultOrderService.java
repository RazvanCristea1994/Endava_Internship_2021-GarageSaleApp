package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.model.Card;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.repository.memory.InMemoryRepository;
import com.endava.garagesaleapplication.service.AssetService;
import com.endava.garagesaleapplication.service.OrderService;
import com.endava.garagesaleapplication.validator.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("orderService")
public class DefaultOrderService implements OrderService {

    @Autowired
    private AssetService assetService;

    @Autowired
    private InMemoryRepository<Order> orderRepository;

    @Autowired
    private InMemoryRepository<Card> cardRepository;

    public static Integer id = 0;

    @Override
    public Order placeOrder(Order order) {

        order = assetService.findAssetsInStock(order);
        OrderValidation.checkShoppingCartValidity(order.getAssetList());
        //card validation here

        order.setTotalPrice(getTotalOrderPrice(order));
        order.setId(DefaultOrderService.id++);

        Optional<Order> optionalOrder = this.orderRepository.save(order);
        if (optionalOrder.isPresent()) {
            throw new UnknownError("Unknown error when saving the order ");
        }
        this.cardRepository.save(order.getCard());

        return order;
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.getAll();
    }

    private double getTotalOrderPrice(Order order) {

        return order.getAssetList()
                .stream()
                .mapToDouble(asset -> this.assetService.getAsset(asset.getId()).getPrice())
                .sum();
    }
}