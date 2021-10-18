package com.endava.garagesaleapplication.facade.converter.asset;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.category.CategoryResponse;
import com.endava.garagesaleapplication.data.issue.IssueResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import com.endava.garagesaleapplication.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetResponseConverter implements Converter<AssetResponse, Asset> {

    @Autowired
    private Converter<CategoryResponse, Category> assetResponseConverter;

    @Autowired
    private Converter<IssueResponse, Issue> issueResponseConverter;

    @Override
    public AssetResponse convert(Asset asset) {
        return new AssetResponse(
                this.assetResponseConverter.convert(asset.getCategory()),
                asset.getPrice(),
                this.issueResponseConverter.convertAll(asset.getIssueList())
        );
    }
}