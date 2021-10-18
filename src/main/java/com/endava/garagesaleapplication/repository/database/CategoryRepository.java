package com.endava.garagesaleapplication.repository.database;

import com.endava.garagesaleapplication.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}