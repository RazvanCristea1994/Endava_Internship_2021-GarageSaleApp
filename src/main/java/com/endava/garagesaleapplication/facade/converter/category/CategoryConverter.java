package com.endava.garagesaleapplication.facade.converter.category;

import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryRequest> {

    @Override
    public Category convert(CategoryRequest categoryRequest) {
        return Category.CategoryBuilder.aCategory().
                withId(categoryRequest.getId()).build();
    }
}

