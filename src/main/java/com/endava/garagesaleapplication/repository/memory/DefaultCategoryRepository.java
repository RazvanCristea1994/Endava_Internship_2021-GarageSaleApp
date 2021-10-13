package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public class DefaultCategoryRepository extends DefaultInMemoryRepository<Category> {

    @Override
    public Integer getIdForEntity(Category category) {
        return category.getId();
    }
}