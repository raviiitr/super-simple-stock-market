package com.jpm.stockmarket.common.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base interface for all Repositories
 */

public interface CommonRepository<K, V> {
	
    long count();

    V create(K key, V value);

    V load(K key);

    V update(K key, V value);

    boolean delete(K key);

    List<V> list();

    void drop();
}
