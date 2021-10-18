package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table
public class Asset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private double price;

    @OneToMany(
            mappedBy = "asset",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Issue> issueList;

    @Column(nullable = false)
    private int quantity;

    @ManyToMany(mappedBy = "assetList")
    private List<Order> orderList;

    public void addToOrderList(Order order) {
        this.orderList.add(order);
    }

    public static final class AssetBuilder {
        private Integer id;
        private Category category;
        private double price;
        private List<Issue> issueList;
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

        public AssetBuilder withIssueList(List<Issue> issueList) {
            this.issueList = issueList;
            return this;
        }

        public AssetBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Asset build() {
            Asset asset = new Asset();
            asset.category = this.category;
            asset.quantity = this.quantity;
            asset.price = this.price;
            asset.issueList = this.issueList;
            asset.id = this.id;
            return asset;
        }
    }
}