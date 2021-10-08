package com.endava.garagesaleapplication.data.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CategoryRequest {

    @NotNull(message = "This is a required field ")
    private Integer id;

    private CategoryRequest() {
    }
}