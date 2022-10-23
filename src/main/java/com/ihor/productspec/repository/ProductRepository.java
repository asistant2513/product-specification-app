package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.ProductMapper;
import com.ihor.productspec.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCTS;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.ADD_PRODUCT_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_PRODUCT_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_PRODUCT_RECORD;


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
    public int addOne(Product item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(ADD_PRODUCT_RECORD);
            st.setString(1, item.getProductCode());
            st.setString(2, item.getProductName());
            st.setLong(3, item.getProductType().getTypeCode());
            return st.executeUpdate();
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return 0;
        }
    }

    @Override
    public Product update(Product item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(UPDATE_PRODUCT_RECORD);
            st.setString(1, item.getProductName());
            st.setLong(2, item.getProductType().getTypeCode());
            st.setString(3, item.getProductCode());
            return item;
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public int deleteOne(Product item) {
        return deleteOneByID(item.getProductCode());
    }

    @Override
    public int deleteOneByID(String id) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(DELETE_PRODUCT_RECORD);
            st.setString(1, id);
            return st.executeUpdate();
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return 0;
        }
    }
}
