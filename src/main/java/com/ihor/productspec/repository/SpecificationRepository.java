package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.SpecificationMapper;
import com.ihor.productspec.model.Specification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_SPECS;
import static com.ihor.productspec.util.JDBCConstants.ADD_SPEC_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_SPEC_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_SPEC_RECORD;

@Repository
@Slf4j
public class SpecificationRepository implements SQLiteRepository<Specification, String> {

    @Autowired
    private JDBCConnection connection;

    @Autowired
    private SpecificationMapper mapper;

    @Override
    public List<Specification> getAll() {
        try (Connection c = connection.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_SPECS);
            return mapper.mapAll(rs);
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public Specification getOneByID(String id) {
        return null;
    }

    @Override
    public int addOne(Specification item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(ADD_SPEC_RECORD);
            st.setString(1, item.getSourceProduct().getProductCode());
            st.setString(2, item.getTargetProduct().getProductCode());
            st.setLong(3, item.getQuantity());
            return st.executeUpdate();
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return 0;
        }
    }

    @Override
    public Specification update(Specification item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(UPDATE_SPEC_RECORD);
            st.setLong(1, item.getQuantity());
            st.setString(2, item.getSourceProduct().getProductCode());
            st.setString(3, item.getTargetProduct().getProductCode());
            return item;
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public int deleteOne(Specification item) {
        try (Connection c = connection.getConnection()) {
            PreparedStatement st = c.prepareStatement(DELETE_SPEC_RECORD);
            st.setString(1, item.getSourceProduct().getProductCode());
            st.setString(2, item.getTargetProduct().getProductCode());
            return st.executeUpdate();
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteOneByID(String s) {
        return 0;
    }
}
