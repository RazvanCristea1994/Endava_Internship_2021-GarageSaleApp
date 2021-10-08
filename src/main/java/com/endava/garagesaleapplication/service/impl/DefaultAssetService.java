package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.repository.memory.InMemoryRepository;
import com.endava.garagesaleapplication.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("assetService")
public class DefaultAssetService implements AssetService {

    @Autowired
    private InMemoryRepository<Asset> assetRepository;

    @Override
    public List<Asset> getAllAssets() {
        return this.assetRepository.getAll();
    }

    @Override
    public Asset getAsset(Integer id) {
        Optional<Asset> optionalAsset = this.assetRepository.get(id);
        if (optionalAsset.isPresent()) {
            return optionalAsset.get();
        }
        throw new IllegalArgumentException("Asset not available");
    }

    @Override
    public Order findAssetsInStock(Order order) {

        StringBuilder message = new StringBuilder();
        List<Asset> assetList = new ArrayList<>();
        order.getAssetList().forEach(
                (asset) -> {
                    Optional<Asset> assetToFind = this.assetRepository.get(asset.getId());
                    if (!assetToFind.isPresent()) {
                        message.append(asset + " \n");
                    } else {
                        assetList.add(assetToFind.get());
                    }
                }
        );

        if (message.length() != 0) {
            throw new IllegalArgumentException("The following assets are not available: \n" + message);
        }
        order.setAssetList(assetList);
        return order;
    }

    @Override
    public void decrementAssets(List<Asset> assetList) {

        assetList.forEach(
                asset -> {
                    asset.setQuantity(asset.getQuantity() - 1);
                    this.assetRepository.update(asset);
                }
        );
    }
}