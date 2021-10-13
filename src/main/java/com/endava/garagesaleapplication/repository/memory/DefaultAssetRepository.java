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
            Category pcCategory = new Category(1, "PC");
            Category phoneCategory = new Category(1, "Phone");
            Category screenCategory = new Category(1, "Screen");

            super.database.put(
                    1, new Asset(1, laptopCategory, 100, new ArrayList<>(Arrays.asList("Space bar not working")), 5));
            super.database.put(
                    2, new Asset(2, laptopCategory, 120, new ArrayList<>(Arrays.asList("Enter button not working")), 4));
            super.database.put(
                    3, new Asset(3, pcCategory, 200, new ArrayList<>(Arrays.asList("Pretty old hardware")), 3));
            super.database.put(
                    4, new Asset(4, phoneCategory, 300, new ArrayList<>(Arrays.asList("Scratches")), 2));
            super.database.put(
                    5, new Asset(5, screenCategory, 400, new ArrayList<>(Arrays.asList("Broken")), 1));
        }
    }

    @Override
    public Integer getIdForEntity(Asset asset) {
        return asset.getId();
    }
}