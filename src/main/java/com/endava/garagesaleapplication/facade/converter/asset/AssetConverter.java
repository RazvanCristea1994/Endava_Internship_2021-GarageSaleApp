package com.endava.garagesaleapplication.facade.converter.asset;

import com.endava.garagesaleapplication.data.asset.AssetRequest;
import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetConverter implements Converter<Asset, AssetRequest> {

    @Autowired
    private Converter<Category, CategoryRequest> categoryConverter;

    @Override
    public Asset convert(AssetRequest assetRequest) {
        return new Asset(
                assetRequest.getId(),
                this.categoryConverter.convert(assetRequest.getCategoryRequest())
        );
    }
}