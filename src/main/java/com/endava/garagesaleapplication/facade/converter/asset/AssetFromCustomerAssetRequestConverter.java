package com.endava.garagesaleapplication.facade.converter.asset;

import com.endava.garagesaleapplication.data.asset.CustomerAssetRequest;
import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetFromCustomerAssetRequestConverter implements Converter<Asset, CustomerAssetRequest> {

    @Autowired
    Converter<Category, CategoryRequest> categoryConverter;

    @Override
    public Asset convert(CustomerAssetRequest customerAssetRequest) {
        return Asset.AssetBuilder.anAsset().
                withId(customerAssetRequest.getId()).build();
    }
}