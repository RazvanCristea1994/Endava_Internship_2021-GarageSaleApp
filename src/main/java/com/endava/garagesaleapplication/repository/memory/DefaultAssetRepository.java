package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("assetRepository")
public class DefaultAssetRepository extends DefaultInMemoryRepository<Asset> {


    public DefaultAssetRepository() {
        if (super.database.isEmpty()) {
            super.database.put(
                    1, new Asset(1, new Category(1, "Laptop"), 100, new ArrayList<String>(Arrays.asList("Space bar not working")), 5));
            super.database.put(
                    2, new Asset(2, new Category(2, "PC"), 200, new ArrayList<String>(Arrays.asList("Pretty old hardware")), 3));
            super.database.put(
                    3, new Asset(3, new Category(3, "Phone"), 300, new ArrayList<String>(Arrays.asList("Scratches")), 4));
            super.database.put(
                    4, new Asset(4, new Category(4, "Screen"), 400, new ArrayList<String>(Arrays.asList("Broken")), 2));
        }
    }

    @Override
    public Integer getIdForEntity(Asset asset) {
        return asset.getId();
    }
}