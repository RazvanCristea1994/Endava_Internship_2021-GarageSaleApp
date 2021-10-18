package com.endava.garagesaleapplication.repository.database;

import com.endava.garagesaleapplication.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(
            value = "SELECT * FROM orders",
            nativeQuery = true
    )
    List<Order> findAllAsList();
}