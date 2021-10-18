package com.endava.garagesaleapplication.facade.converter.asset;

import com.endava.garagesaleapplication.data.asset.NewAssetRequest;
import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.data.issue.IssueRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import com.endava.garagesaleapplication.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetFromNewAssetRequestConverter implements Converter<Asset, NewAssetRequest> {

    @Autowired
    private Converter<Category, CategoryRequest> categoryConverter;

    @Autowired
    private Converter<Issue, IssueRequest> issueConverter;

    public Asset convert(NewAssetRequest newAssetRequest) {
        return Asset.AssetBuilder.anAsset()
                .withCategory(this.categoryConverter.convert(newAssetRequest.getCategoryRequest()))
                .withPrice(newAssetRequest.getPrice())
                .withIssueList(this.issueConverter.convertAll(newAssetRequest.getIssues()))
                .withQuantity(newAssetRequest.getQuantity()).build();
    }
}