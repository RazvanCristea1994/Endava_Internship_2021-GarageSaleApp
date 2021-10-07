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
public class Asset {

    private Integer id;
    private Category category;
    private double Price;
    private List<String> issues;
    private int quantity;

    public Asset(Integer id, Category category) {
        this.id = id;
        this.category = category;
    }
}