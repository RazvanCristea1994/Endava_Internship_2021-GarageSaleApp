package com.endava.garagesaleapplication.facade.converter.asset;

import com.endava.garagesaleapplication.data.asset.AssetRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetConverter implements Converter<Asset, AssetRequest> {

    @Override
    public Asset convert(AssetRequest assetRequest) {
        return new Asset(assetRequest.getId());
    }
}