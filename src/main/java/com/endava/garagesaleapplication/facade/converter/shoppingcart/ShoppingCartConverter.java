package com.endava.garagesaleapplication.facade.converter.shoppingcart;

import com.endava.garagesaleapplication.data.asset.AssetRequest;
import com.endava.garagesaleapplication.data.card.CardRequest;
import com.endava.garagesaleapplication.data.shoppingcart.ShoppingCartRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Card;
import com.endava.garagesaleapplication.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartConverter implements Converter<ShoppingCart, ShoppingCartRequest> {

    @Autowired
    private Converter<Asset, AssetRequest> assetConverter;

    @Autowired
    private Converter<Card, CardRequest> cardConverter;

    @Override
    public ShoppingCart convert(ShoppingCartRequest shoppingCartRequest) {

        return new ShoppingCart(
                this.assetConverter.convertAll(shoppingCartRequest.getAssetRequestList()),
                shoppingCartRequest.getCustomerName(),
                shoppingCartRequest.getEmail(),
                this.cardConverter.convert(shoppingCartRequest.getCardRequest())
        );
    }
}