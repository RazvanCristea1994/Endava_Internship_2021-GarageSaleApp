package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Card;
import org.springframework.stereotype.Repository;

/**
 * Deprecated after DB connection was implemented
 */
@Repository
public class DefaultCardRepository extends DefaultInMemoryRepository<Card> {

    @Override
    public Integer getIdForEntity(Card card) {
        return card.getId();
    }
}