package com.endava.garagesaleapplication.service;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Order;

import java.util.List;

public interface AssetService {

    Asset save(Asset newAsset);

    List<Asset> getAllAvailableAssets();

    Asset findById(Integer id);

    List<Asset> findOrderedAssetsInDb(Order order);

    void decrementAssetsByOne(List<Asset> assetList);

    void deleteAssetList(List<Asset> assetList);
}