package com.endava.garagesaleapplication.facade.converter.order;

import com.endava.garagesaleapplication.data.asset.CustomerAssetRequest;
import com.endava.garagesaleapplication.data.card.CardRequest;
import com.endava.garagesaleapplication.data.order.OrderRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Card;
import com.endava.garagesaleapplication.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<Order, OrderRequest> {

    @Autowired
    private Converter<Asset, CustomerAssetRequest> assetConverter;

    @Autowired
    private Converter<Card, CardRequest> cardConverter;

    @Override
    public Order convert(OrderRequest orderRequest) {
        return Order.OrderBuilder
                .anOrder()
                .withAssetList(this.assetConverter.convertAll(orderRequest.getCustomerAssetRequestList()))
                .withCustomerName(orderRequest.getCustomerName())
                .withEmail(orderRequest.getEmail())
                .withCard(this.cardConverter.convert(orderRequest.getCardRequest())).build();
    }
}