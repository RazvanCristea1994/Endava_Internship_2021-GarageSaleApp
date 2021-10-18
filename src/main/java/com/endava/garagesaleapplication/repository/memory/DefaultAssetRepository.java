package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Asset;
import org.springframework.stereotype.Repository;

@Repository("assetRepository")
public class DefaultAssetRepository extends DefaultInMemoryRepository<Asset> {

    @Override
    public Integer getIdForEntity(Asset asset) {
        return asset.getId();
    }
}