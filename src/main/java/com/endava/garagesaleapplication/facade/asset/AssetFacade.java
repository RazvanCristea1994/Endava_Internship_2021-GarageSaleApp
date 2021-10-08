package com.endava.garagesaleapplication.facade.asset;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.asset.AssetStockResponse;
import com.endava.garagesaleapplication.model.Asset;

import java.util.List;

public interface AssetFacade {

    List<AssetResponse> getAssetResponse(List<Asset> assetList);

    List<AssetStockResponse> getAssetStockResponse(List<Asset> assetList);
}