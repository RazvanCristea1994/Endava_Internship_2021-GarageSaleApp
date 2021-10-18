package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Asset;
import org.springframework.stereotype.Repository;

/**
 * Deprecated after DB connection was implemented
 */
@Repository
public class DefaultAssetRepository extends DefaultInMemoryRepository<Asset> {

    @Override
    public Integer getIdForEntity(Asset asset) {
        return asset.getId();
    }
}