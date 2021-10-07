package com.endava.garagesaleapplication.facade.asset;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultAssetFacade implements AssetFacade {

    @Autowired
    Converter<AssetResponse, Asset> assetResponseConverter;

    @Override
    public List<AssetResponse> getAssetResponse(List<Asset> assetList) {
        return this.assetResponseConverter.convertAll(assetList);
    }
}