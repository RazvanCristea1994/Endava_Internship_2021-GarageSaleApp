package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Order implements Serializable {

    private Integer id;
    private List<Asset> assetList;
    private String customerName;
    private String email;
    private Card card;
    private Double totalPrice;

    private Order() {
    }

    public static final class OrderBuilder {
        private Integer id;
        private List<Asset> assetList;
        private String customerName;
        private String email;
        private Card card;
        private Double totalPrice;

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

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setAssetList(assetList);
            order.setCustomerName(customerName);
            order.setEmail(email);
            order.setCard(card);
            order.setTotalPrice(totalPrice);
            return order;
        }
    }
}