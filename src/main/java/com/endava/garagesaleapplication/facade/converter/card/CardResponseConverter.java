package com.endava.garagesaleapplication.facade.converter.card;

import com.endava.garagesaleapplication.data.card.CardResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardResponseConverter implements Converter<CardResponse, Card> {

    @Override
    public CardResponse convert(Card card) {
        return new CardResponse(card.getCardHolderName());
    }
}