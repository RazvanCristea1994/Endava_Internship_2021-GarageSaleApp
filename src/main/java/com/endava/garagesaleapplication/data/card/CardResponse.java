package com.endava.garagesaleapplication.data.card;

import lombok.Getter;

@Getter
public class CardResponse {

    private final String cardHolderName;
    private final String cardNumber = "*".repeat(16);
    private final String expiry = "*".repeat(4) + "-" + "*".repeat(2) + "-" + "*".repeat(2);
    private final String civ = "*".repeat(3);

    public CardResponse(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public String toString() {
        return "\nCard details: \n" +
                "Card Holder Name: " + cardHolderName + '\n' +
                "Card Number: " + cardNumber + '\n' +
                "Expiry: " + expiry + '\n' +
                "CIV: " + civ + "\n";
    }
}