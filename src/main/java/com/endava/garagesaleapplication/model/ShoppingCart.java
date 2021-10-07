package com.endava.garagesaleapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    private Integer id;
    private List<Asset> assetList;
    private String customerName;
    private String email;
    private Card card;
    private Double totalPrice;

    public ShoppingCart(List<Asset> assetList, String customerName, String email, Card card) {
        this.assetList = assetList;
        this.customerName = customerName;
        this.email = email;
        this.card = card;
    }
}