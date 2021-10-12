package com.endava.garagesaleapplication.facade.order;

import com.endava.garagesaleapplication.data.order.OrderRequest;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.model.Order;

import java.util.List;

public interface OrderFacade {

    Order convertToOrder(OrderRequest orderRequest);

    OrderResponse convertToOrderResponse(Order order);

    List<OrderResponse> getAll(List<Order> orderList);
}