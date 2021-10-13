package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

@Repository("assetRepository")
public class DefaultAssetRepository extends DefaultInMemoryRepository<Asset> {

    public DefaultAssetRepository() {
        if (super.database.isEmpty()) {
            Category laptopCategory = new Category(1, "Laptop");

            super.database.put(
                    1, new Asset(1, laptopCategory, 100, new ArrayList<>(Arrays.asList("Space bar not working")), 5));
            super.database.put(
                    2, new Asset(2, laptopCategory, 120, new ArrayList<>(Arrays.asList("Enter button not working")), 4));
            super.database.put(
                    3, new Asset(3, new Category(2, "PC"), 200, new ArrayList<>(Arrays.asList("Pretty old hardware")), 3));
            super.database.put(
                    4, new Asset(4, new Category(3, "Phone"), 300, new ArrayList<>(Arrays.asList("Scratches")), 2));
            super.database.put(
                    5, new Asset(5, new Category(4, "Screen"), 400, new ArrayList<>(Arrays.asList("Broken")), 1));
        }
    }

    @Override
    public Integer getIdForEntity(Asset asset) {
        return asset.getId();
    }
}