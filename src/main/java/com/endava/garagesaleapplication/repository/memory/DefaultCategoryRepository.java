package com.endava.garagesaleapplication.repository.memory;

import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public class DefaultCategoryRepository extends DefaultInMemoryRepository<Category> {

    public DefaultCategoryRepository() {
        Category laptopCategory = new Category(1, "Laptop");
        Category pcCategory = new Category(1, "PC");
        Category phoneCategory = new Category(1, "Phone");
        Category screenCategory = new Category(1, "Screen");

        super.save(laptopCategory);
        super.save(pcCategory);
        super.save(phoneCategory);
        super.save(screenCategory);
    }

    @Override
    public Integer getIdForEntity(Category category) {
        return category.getId();
    }
}