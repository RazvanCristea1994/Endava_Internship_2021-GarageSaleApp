package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Category implements Serializable {

    private Integer id;
    private String name;

    private Category() {
    }

    public static final class CategoryBuilder {
        private Integer id;
        private String name;

        private CategoryBuilder() {
        }

        public static CategoryBuilder aCategory() {
            return new CategoryBuilder();
        }

        public CategoryBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            return category;
        }
    }
}