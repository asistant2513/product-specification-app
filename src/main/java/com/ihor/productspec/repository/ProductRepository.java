package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.ProductMapper;
import com.ihor.productspec.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCTS;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_BY_ID;

@Repository
@Slf4j
public class ProductRepository implements SQLiteRepository<Product, String> {

    @Autowired
    private JDBCConnection connection;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        try (Connection c = connection.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_PRODUCTS);
            return mapper.mapAll(rs);
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public Product getOneByID(String id) {
        try (Connection c = connection.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(String.format(SELECT_ONE_PRODUCT_BY_ID, id));
            return mapper.mapOne(rs);
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public String addOne(Product item) {
        return null;
    }

    @Override
    public Product update(Product item) {
        return null;
    }

    @Override
    public String deleteOne(Product item) {
        return null;
    }

    @Override
    public void deleteAll(List<Product> items) {

    }
}
