package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Category() {
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