package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Asset implements Serializable {

    private Integer id;
    private Category category;
    private double price;
    private List<String> issues;
    private int quantity;

    private Asset() {
    }

    public static final class AssetBuilder {
        private Integer id;
        private Category category;
        private double price;
        private List<String> issues;
        private int quantity;

        private AssetBuilder() {
        }

        public static AssetBuilder anAsset() {
            return new AssetBuilder();
        }

        public AssetBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AssetBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public AssetBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public AssetBuilder withIssues(List<String> issues) {
            this.issues = issues;
            return this;
        }

        public AssetBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Asset build() {
            Asset asset = new Asset();
            asset.setId(id);
            asset.setCategory(category);
            asset.setPrice(price);
            asset.setIssues(issues);
            asset.setQuantity(quantity);
            return asset;
        }
    }
}