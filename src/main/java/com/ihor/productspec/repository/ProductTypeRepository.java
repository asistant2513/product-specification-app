package com.ihor.productspec.repository;

import com.ihor.productspec.model.ProductType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCT_TYPES;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_TYPE_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.ADD_PRODUCT_TYPE_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_PRODUCT_TYPE_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_PRODUCT_TYPE_RECORD;

@Repository
public interface ProductTypeRepository {

    @Query(SELECT_ALL_PRODUCT_TYPES)
    List<ProductType> getAll();

    @Query(SELECT_ONE_PRODUCT_TYPE_BY_ID)
    ProductType getOneByID(@Param("typeId") Long id);

    @Query(ADD_PRODUCT_TYPE_RECORD)
    int addOne(@Param("typeId") long typeId, @Param("typeName") String typeName);

    @Query(UPDATE_PRODUCT_TYPE_RECORD)
    int update(@Param("typeId") long typeId, @Param("typeName") String typeName);

    @Query(DELETE_PRODUCT_TYPE_RECORD)
    int deleteOneByID(@Param("typeId") long id);
}
