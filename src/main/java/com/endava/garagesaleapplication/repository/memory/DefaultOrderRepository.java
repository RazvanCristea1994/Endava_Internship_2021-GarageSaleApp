package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Order;
import org.springframework.stereotype.Repository;

/**
 * Deprecated after DB connection was implemented
 */
@Repository
public class DefaultOrderRepository extends DefaultInMemoryRepository<Order> {

    @Override
    public Integer getIdForEntity(Order order) {
        return order.getId();
    }
}