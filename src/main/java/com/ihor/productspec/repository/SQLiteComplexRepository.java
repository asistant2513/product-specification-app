package com.ihor.productspec.repository;

import com.ihor.productspec.model.Component;

import java.util.List;

public interface SQLiteComplexRepository <T, ID>{

    List<T> getAllFirstLevelNodesById(final ID id);

    List<T> getAllFirstLevelUsabilityById(final ID id);
}
