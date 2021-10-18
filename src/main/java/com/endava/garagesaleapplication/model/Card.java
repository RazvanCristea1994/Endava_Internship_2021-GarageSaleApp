package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Card implements Serializable {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String cardHolderName;

    @Transient
    private String cardNumber;

    @Transient
    private LocalDate expiry;

    @Transient
    private String civ;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Order order;

    private Card() {
    }

    public static final class CardBuilder {
        private Integer id;
        private String cardHolderName;
        private String cardNumber;
        private LocalDate expiry;
        private String civ;

        private CardBuilder() {
        }

        public static CardBuilder aCard() {
            return new CardBuilder();
        }

        public CardBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public CardBuilder withCardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public CardBuilder withCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardBuilder withExpiry(LocalDate expiry) {
            this.expiry = expiry;
            return this;
        }

        public CardBuilder withCiv(String civ) {
            this.civ = civ;
            return this;
        }

        public Card build() {
            Card card = new Card();
            card.setId(id);
            card.setCardHolderName(cardHolderName);
            card.setCardNumber(cardNumber);
            card.setExpiry(expiry);
            card.setCiv(civ);
            return card;
        }
    }
}