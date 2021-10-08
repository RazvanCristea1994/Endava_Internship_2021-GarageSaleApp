package com.endava.garagesaleapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    private Integer id;
    private String cardHolderName;
    private String cardNumber;
    private LocalDate expiry;
    private String civ;

    public Card(String cardHolderName, String cardNumber, LocalDate expiry, String civ) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.civ = civ;
    }
}