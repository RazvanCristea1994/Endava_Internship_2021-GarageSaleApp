package com.endava.garagesaleapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private Integer id;
    private List<Asset> assetList;
    private String customerName;
    private String email;
    private Card card;
    private Double totalPrice;

    public Order(List<Asset> assetList, String customerName, String email, Card card) {
        this.assetList = assetList;
        this.customerName = customerName;
        this.email = email;
        this.card = card;
    }
}