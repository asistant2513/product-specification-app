package com.ihor.productspec.repository;

import java.util.List;

public interface SQLiteRepository <T, ID> {

    List<T> getAll();

    T getOneByID(final ID id);

    int addOne(final T item);

    T update(final T item);

    int deleteOne(final T item);

    int deleteOneByID(final ID id);
}
