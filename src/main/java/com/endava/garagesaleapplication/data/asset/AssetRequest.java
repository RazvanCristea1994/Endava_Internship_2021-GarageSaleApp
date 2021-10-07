package com.endava.garagesaleapplication.data.asset;

import com.endava.garagesaleapplication.data.category.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AssetRequest {

    @NotNull(message = "This is a required field ")
    private final Integer id;

    @Valid
    @NotNull(message = "This is a required field ")
    private final CategoryRequest categoryRequest;
}