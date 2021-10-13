package com.endava.garagesaleapplication.facade.converter;

import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.model.Category;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Converter<Target, Source> {

    Target convert(Source source);

    default List<Target> convertAll(List<Source> sourceList) {

        return sourceList.stream()
                .map(this::convert)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}