package com.Car_Rental_Spring.repositiry;

import java.util.List;

public interface GenericDao<T, K> {

    List<T> findAll();

    T findById(K id);

    void delete(K id);

    T save(T entity);

    T update(T entity);
}