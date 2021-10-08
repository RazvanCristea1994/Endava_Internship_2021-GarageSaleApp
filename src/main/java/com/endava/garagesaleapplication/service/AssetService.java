package com.endava.garagesaleapplication.service;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Order;

import java.util.List;

public interface AssetService {

    List<Asset> getAllAssets();

    Asset getAsset(Integer id);

    Order findAssetsInStock(Order order);
}