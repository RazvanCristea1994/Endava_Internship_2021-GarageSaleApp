package com.endava.garagesaleapplication.repository.database;

import com.endava.garagesaleapplication.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
}