package com.endava.garagesaleapplication.facade.shoppingcart;

import com.endava.garagesaleapplication.data.shoppingcart.ShoppingCartRequest;
import com.endava.garagesaleapplication.data.shoppingcart.ShoppingCartResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultShoppingCartFacade implements ShoppingCartFacade {

    @Autowired
    private Converter<ShoppingCart, ShoppingCartRequest> shoppingCartConverter;

    @Autowired
    private Converter<ShoppingCartResponse, ShoppingCart> shoppingCartResponseConverter;

    @Override
    public ShoppingCart getShoppingCart(ShoppingCartRequest shoppingCartRequest) {
        return this.shoppingCartConverter.convert(shoppingCartRequest);
    }

    @Override
    public ShoppingCartResponse getShoppingCartResponse(ShoppingCart shoppingCart) {
        return this.shoppingCartResponseConverter.convert(shoppingCart);
    }

    @Override
    public List<ShoppingCartResponse> getAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}