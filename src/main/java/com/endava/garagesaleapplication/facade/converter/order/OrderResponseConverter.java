package com.endava.garagesaleapplication.facade.converter.order;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.card.CardResponse;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Card;
import com.endava.garagesaleapplication.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseConverter implements Converter<OrderResponse, Order> {

    @Autowired
    private Converter<AssetResponse, Asset> assetResponseConverter;

    @Autowired
    private Converter<CardResponse, Card> cardResponseConverter;

    @Override
    public OrderResponse convert(Order order) {
        return new OrderResponse(
                this.assetResponseConverter.convertAll(order.getAssetList()),
                order.getTotalPrice(),
                this.cardResponseConverter.convert(order.getCard())
        );
    }
}