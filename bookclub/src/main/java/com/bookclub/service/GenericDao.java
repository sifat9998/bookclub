package com.bookclub.service.dao;

import java.util.List;

public interface GenericDao<E, K> {

    void add(E entity);

    void update(E entity);

    boolean remove(E entity);

    List<E> list();

    E find(K key);
}