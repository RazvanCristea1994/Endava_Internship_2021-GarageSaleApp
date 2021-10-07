package com.endava.garagesaleapplication.facade.shoppingcart;

import com.endava.garagesaleapplication.data.shoppingcart.ShoppingCartRequest;
import com.endava.garagesaleapplication.data.shoppingcart.ShoppingCartResponse;
import com.endava.garagesaleapplication.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartFacade {

    ShoppingCart getShoppingCart(ShoppingCartRequest shoppingCartRequest);

    ShoppingCartResponse getShoppingCartResponse(ShoppingCart shoppingCart);

    List<ShoppingCartResponse> getAll();

    void delete(Integer id);
}