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
    private double Price;
    private List<String> issues;

    public Asset(Integer id) {
        this.id = id;
    }
}