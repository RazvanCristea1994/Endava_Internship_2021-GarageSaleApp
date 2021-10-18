package com.endava.garagesaleapplication.data.asset;

import com.endava.garagesaleapplication.data.category.CategoryResponse;
import com.endava.garagesaleapplication.data.issue.IssueResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AssetStockResponse {

    private final Integer id;
    private final CategoryResponse categoryResponse;
    private final double Price;
    private final List<IssueResponse> issues;
    private final int quantity;
}