package com.endava.garagesaleapplication.validator;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderValidation {

    public static void checkShoppingCartValidity(List<Asset> assetList) {

        if (assetList.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one item in your shopping cart ");
        }

        Map<Category, Asset> assetRequestMap = new HashMap<>();
        for (Asset asset : assetList) {
            if (!assetRequestMap.containsKey(asset.getCategory())) {
                assetRequestMap.put(asset.getCategory(), asset);
            } else {
                throw new IllegalArgumentException("You can purchase only one asset from each category ");
            }
        }
    }
}