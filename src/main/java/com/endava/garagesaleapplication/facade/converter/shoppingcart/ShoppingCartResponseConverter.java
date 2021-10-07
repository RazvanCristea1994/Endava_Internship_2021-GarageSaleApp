package com.endava.garagesaleapplication.facade.converter.shoppingcart;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.card.CardResponse;
import com.endava.garagesaleapplication.data.shoppingcart.ShoppingCartResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Card;
import com.endava.garagesaleapplication.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartResponseConverter implements Converter<ShoppingCartResponse, ShoppingCart> {

    @Autowired
    private Converter<AssetResponse, Asset> assetResponseConverter;

    @Autowired
    private Converter<CardResponse, Card> cardResponseConverter;

    @Override
    public ShoppingCartResponse convert(ShoppingCart shoppingCart) {
        return new ShoppingCartResponse(
                this.assetResponseConverter.convertAll(shoppingCart.getAssetList()),
                shoppingCart.getTotalPrice(),
                this.cardResponseConverter.convert(shoppingCart.getCard())
        );
    }
}