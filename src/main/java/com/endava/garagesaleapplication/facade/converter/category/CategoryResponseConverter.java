package com.endava.garagesaleapplication.facade.converter.category;

import com.endava.garagesaleapplication.data.category.CategoryResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseConverter implements Converter<CategoryResponse, Category> {

    @Override
    public CategoryResponse convert(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}