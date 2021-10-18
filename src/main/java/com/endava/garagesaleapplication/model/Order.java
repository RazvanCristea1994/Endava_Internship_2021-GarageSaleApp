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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "order_asset",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id"))
    private List<Asset> assetList;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String email;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Card card;

    @Column(nullable = false)
    private Double totalPrice;

    @Column
    private LocalDateTime orderDateTime;

    private Order() {
    }

    public static final class OrderBuilder {
        private Integer id;
        private List<Asset> assetList;
        private String customerName;
        private String email;
        private Card card;
        private Double totalPrice;
        private LocalDateTime orderDateTime;

        private OrderBuilder() {
        }

        public static OrderBuilder anOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withAssetList(List<Asset> assetList) {
            this.assetList = assetList;
            return this;
        }

        public OrderBuilder withCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public OrderBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public OrderBuilder withCard(Card card) {
            this.card = card;
            return this;
        }

        public OrderBuilder withTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderBuilder withOrderDateTime(LocalDateTime orderDateTime) {
            this.orderDateTime = orderDateTime;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setAssetList(assetList);
            order.setCustomerName(customerName);
            order.setEmail(email);
            order.setCard(card);
            order.setTotalPrice(totalPrice);
            order.setOrderDateTime(orderDateTime);
            return order;
        }
    }
}