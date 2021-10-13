package com.endava.garagesaleapplication.facade.asset;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.asset.AssetStockResponse;
import com.endava.garagesaleapplication.data.asset.NewAssetRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultAssetFacade implements AssetFacade {

    @Autowired
    Converter<AssetResponse, Asset> assetResponseConverter;

    @Autowired
    Converter<AssetStockResponse, Asset> assetStockResponseConverter;

    @Autowired
    Converter<Asset, NewAssetRequest> assetConverter;

    @Override
    public List<AssetResponse> getAssetResponse(List<Asset> assetList) {
        return this.assetResponseConverter.convertAll(assetList);
    }

    @Override
    public List<AssetStockResponse> getAssetStockResponse(List<Asset> assetList) {
        return this.assetStockResponseConverter.convertAll(assetList);
    }

    @Override
    public Asset convertToAsset(NewAssetRequest newAssetRequest) {
        return this.assetConverter.convert(newAssetRequest);
    }

    @Override
    public AssetResponse convertToAssetResponse(Asset asset) {
        return this.assetResponseConverter.convert(asset);
    }
}