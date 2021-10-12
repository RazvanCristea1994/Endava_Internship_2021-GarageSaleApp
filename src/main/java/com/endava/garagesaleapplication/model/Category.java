package com.endava.garagesaleapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private Integer id;
    private String name;
    private int quantity;

    public Category(Integer id) {
        this.id = id;
    }
}