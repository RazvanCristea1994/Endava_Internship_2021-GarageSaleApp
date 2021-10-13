package com.endava.garagesaleapplication.facade.converter.asset;

import com.endava.garagesaleapplication.data.asset.NewAssetRequest;
import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetFromNewAssetRequestConverter implements Converter<Asset, NewAssetRequest> {

    @Autowired
    Converter<Category, CategoryRequest> categoryConverter;

    public Asset convert(NewAssetRequest newAssetRequest) {
        return new Asset(this.categoryConverter.convert(newAssetRequest.getCategoryRequest()),
                newAssetRequest.getPrice(),
                newAssetRequest.getIssues());
    }
}