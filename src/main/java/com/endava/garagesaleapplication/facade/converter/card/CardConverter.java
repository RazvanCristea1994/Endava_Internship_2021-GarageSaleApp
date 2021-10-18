package com.endava.garagesaleapplication.facade.converter.card;

import com.endava.garagesaleapplication.data.card.CardRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConverter implements Converter<Card, CardRequest> {

    @Override
    public Card convert(CardRequest cardRequest) {
        return Card.CardBuilder.aCard()
                .withCardHolderName(cardRequest.getCardHolderName())
                .withCardNumber(cardRequest.getCardNumber())
                .withExpiry(cardRequest.getExpiry())
                .withCiv(cardRequest.getCiv()).build();
    }
}