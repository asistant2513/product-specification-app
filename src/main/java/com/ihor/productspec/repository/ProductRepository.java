package com.ihor.productspec.repository;

import com.ihor.productspec.model.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCTS;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.ADD_PRODUCT_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_PRODUCT_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_PRODUCT_RECORD;


@Repository
public interface ProductRepository {

    @Query(SELECT_ALL_PRODUCTS)
    List<Product> getAll();

    @Query(SELECT_ONE_PRODUCT_BY_ID)
    Product getOneByID(@Param("productId") String id);

    @Query(ADD_PRODUCT_RECORD)
    int addOne(@Param("productId") String id, @Param("productName") String name, @Param("productType") long typeId);

    @Query(UPDATE_PRODUCT_RECORD)
    int update(@Param("productId") String id, @Param("productName") String name, @Param("productType") long typeId);

    @Query(DELETE_PRODUCT_RECORD)
    int deleteOneByID(@Param("productId") String id);
}
