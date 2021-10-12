package com.endava.garagesaleapplication.facade.order;

import com.endava.garagesaleapplication.data.order.OrderRequest;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultOrderFacade implements OrderFacade {

    @Autowired
    private Converter<Order, OrderRequest> orderConverter;

    @Autowired
    private Converter<OrderResponse, Order> orderResponseConverter;

    @Override
    public Order convertToOrder(OrderRequest orderRequest) {
        return this.orderConverter.convert(orderRequest);
    }

    @Override
    public OrderResponse convertToOrderResponse(Order order) {
        return this.orderResponseConverter.convert(order);
    }

    @Override
    public List<OrderResponse> getAll(List<Order> orderList) {
        return this.orderResponseConverter.convertAll(orderList);
    }
}