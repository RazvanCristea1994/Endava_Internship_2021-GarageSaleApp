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
import java.util.stream.Collectors;

@Service("assetService")
public class DefaultAssetService implements AssetService {

    @Autowired
    private InMemoryRepository<Asset> assetRepository;

    @Override
    public List<Asset> getAllAvailableAssets() {
        List<Asset> assetList = this.assetRepository.getAll();
        assetList = getOnlyAvailableAssets(assetList);

        return assetList;
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
    public List<Asset> findOrderedAssetsInDB(Order order) {

        StringBuilder message = new StringBuilder();
        List<Asset> assetList = new ArrayList<>();
        order.getAssetList().forEach(
                (asset) -> {
                    Optional<Asset> assetToFind = this.assetRepository.get(asset.getId());
                    if (!assetToFind.isPresent() || assetToFind.get().getCategory().getQuantity() < 1) {
                        message.append(asset.getId() + ", ");
                    } else {
                        assetList.add(assetToFind.get());
                    }
                }
        );

        if (message.length() != 0) {
            throw new IllegalArgumentException("The following assets are not available: " + message);
        }

        return assetList;
    }

    @Override
    public void decrementAssets(List<Asset> assetList) {

        assetList.forEach(
                asset -> {
                    asset.getCategory().setQuantity(asset.getCategory().getQuantity() - 1);
                    this.assetRepository.update(asset);
                }
        );
    }

    @Override
    public void deleteAssetList(List<Asset> assetList) {
        assetList.forEach(
                asset -> this.assetRepository.delete(asset.getId()));
    }

    private List<Asset> getOnlyAvailableAssets(List<Asset> assetList) {
        return assetList.stream()
                .filter(asset -> asset.getCategory().getQuantity() > 0)
                .collect(Collectors.toList());
    }
}