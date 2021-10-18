package com.endava.garagesaleapplication.data.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private final String name;

    @Override
    public String toString() {
        return "\tCategory: " + name;
    }
}