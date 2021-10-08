package com.endava.garagesaleapplication.service;

import com.endava.garagesaleapplication.model.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> getAll();
}