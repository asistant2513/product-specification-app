package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.ProductTypeMapper;
import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.*;

@Repository
@Slf4j
public class ProductTypeRepository implements SQLiteRepository<ProductType, Long> {

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
    public ProductType getOneByID(Long id) {
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
    public int addOne(ProductType item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(ADD_PRODUCT_TYPE_RECORD);
            st.setLong(1, item.getTypeCode());
            st.setString(2, item.getTypeName());
            return st.executeUpdate();
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return 0;
        }
    }

    @Override
    public ProductType update(ProductType item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(UPDATE_PRODUCT_TYPE_RECORD);
            st.setString(1, item.getTypeName());
            st.setLong(2, item.getTypeCode());
            return item;
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public int deleteOne(ProductType item) {
        return deleteOneByID(item.getTypeCode());
    }

    @Override
    public int deleteOneByID(Long id) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(DELETE_PRODUCT_TYPE_RECORD);
            st.setLong(1, id);
            return st.executeUpdate();
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return 0;
        }
    }
}
