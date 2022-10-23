package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.ProductTypeMapper;
import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCT_TYPES;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_TYPE_BY_ID;

@Repository
@Slf4j
public class ProductTypeRepository implements SQLiteRepository<ProductType, Integer> {

    @Autowired
    private JDBCConnection connection;

    @Autowired
    private ProductTypeMapper mapper;

    @Override
    public List<ProductType> getAll() {
        try (Connection c = connection.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_PRODUCT_TYPES);
            return mapper.mapAll(rs);
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public ProductType getOneByID(Integer id) {
        try (Connection c = connection.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(String.format(SELECT_ONE_PRODUCT_TYPE_BY_ID, id));
            return mapper.mapOne(rs);
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public Integer addOne(ProductType item) {
        return null;
    }

    @Override
    public ProductType update(ProductType item) {
        return null;
    }

    @Override
    public Integer deleteOne(ProductType item) {
        return null;
    }

    @Override
    public void deleteAll(List<ProductType> items) {

    }
}
