package com.ihor.productspec.repository;

import java.util.List;

public interface SQLiteRepository <T, ID> {

    List<T> getAll();

    T getOneByID(final ID id);

    ID addOne(final T item);

    T update(final T item);

    ID deleteOne(final T item);

    void deleteAll(final List<T> items);

}
