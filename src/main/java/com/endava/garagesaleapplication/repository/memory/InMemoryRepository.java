package com.endava.garagesaleapplication.repository.memory;

import java.util.List;
import java.util.Optional;

public interface InMemoryRepository<T> {

    Optional<T> save(T entity);

    List<T> getAll();

    Optional<T> get(Integer id);

    Optional<T> update(T entity);

    Optional<T> delete(Integer id);
}