package com.endava.garagesaleapplication.data.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CategoryRequest {

    @NotNull(message = "[ID] is a required field ")
    private Integer id;

    private CategoryRequest() {
    }
}