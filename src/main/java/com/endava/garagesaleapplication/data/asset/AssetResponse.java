package com.endava.garagesaleapplication.data.asset;

import com.endava.garagesaleapplication.data.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AssetResponse {

    private final CategoryResponse categoryResponse;
    private final double Price;
    private final List<String> issues;
}