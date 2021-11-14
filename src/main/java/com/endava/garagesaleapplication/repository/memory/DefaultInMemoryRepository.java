package com.endava.garagesaleapplication.repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Deprecated after DB connection was implemented
 */
public abstract class DefaultInMemoryRepository<T> implements InMemoryRepository<T> {

    protected Map<Integer, T> database;

    public DefaultInMemoryRepository() {
        this.database = new HashMap<>();
    }

    public abstract Integer getIdForEntity(T entity);

    @Override
    public Optional<T> save(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Integer id = getIdForEntity(entity);
        return Optional.ofNullable(database.putIfAbsent(id, entity));
    }

    @Override
    public List<T> getAll() {

        List<T> results = new ArrayList<>();
        results.addAll(database.values());

        return results;
    }

    @Override
    public Optional<T> get(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Optional<T> update(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Integer id = getIdForEntity(entity);
        return Optional.ofNullable(database.computeIfPresent(id, (k, v) -> entity));
    }

    @Override
    public Optional<T> delete(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(database.remove(id));
    }
}