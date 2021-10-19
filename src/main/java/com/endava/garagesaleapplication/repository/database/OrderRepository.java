package com.endava.garagesaleapplication.repository.database;

import com.endava.garagesaleapplication.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    /**
     * This custom query is meant to provide all the orders directly as a list
     * because the default method provides an Iterator
     *
     * @return {@link Order} a list of orders
     */
    @Query(
            value = "SELECT * FROM orders",
            nativeQuery = true
    )
    List<Order> findAllAsList();
}