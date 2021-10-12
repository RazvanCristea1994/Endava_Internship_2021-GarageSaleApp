package com.endava.garagesaleapplication.facade.converter.order;

import com.endava.garagesaleapplication.data.asset.AssetRequest;
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
    private Converter<Asset, AssetRequest> assetConverter;

    @Autowired
    private Converter<Card, CardRequest> cardConverter;

    @Override
    public Order convert(OrderRequest orderRequest) {
        return new Order(
                this.assetConverter.convertAll(orderRequest.getAssetRequestList()),
                orderRequest.getCustomerName(),
                orderRequest.getEmail(),
                this.cardConverter.convert(orderRequest.getCardRequest())
        );
    }
}