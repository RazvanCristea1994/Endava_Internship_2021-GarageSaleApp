package com.endava.garagesaleapplication.data.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private final Integer id;
    private final String name;
    private final int quantity;
}