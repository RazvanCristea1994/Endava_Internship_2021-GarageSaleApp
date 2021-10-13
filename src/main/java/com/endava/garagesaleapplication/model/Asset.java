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
public class Asset implements Serializable {

    private Integer id;
    private Category category;
    private double price;
    private List<String> issues;
    private int quantity;

    public Asset(Integer id) {
        this.id = id;
    }

    public Asset(Category category, double price, List<String> issues, int quantity) {
        this.category = category;
        this.price = price;
        this.issues = issues;
        this.quantity = quantity;
    }
}