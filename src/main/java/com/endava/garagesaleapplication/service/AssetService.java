package com.endava.garagesaleapplication.service;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Order;

import java.util.List;

public interface AssetService {

    List<Asset> getAllAvailableAssets();

    Asset getAsset(Integer id);

    List<Asset> findOrderedAssetsInDB(Order order);

    void decrementAssets(List<Asset> assetList);

    void deleteAssetList(List<Asset> assetList);
}