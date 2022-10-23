package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.ProductTypeMapper;
import com.ihor.productspec.mapping.SpecificationMapper;
import com.ihor.productspec.model.Specification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_SPECS;

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
    public Specification getOneByID(String s) {
        return null;
    }

    @Override
    public String addOne(Specification item) {
        return null;
    }

    @Override
    public Specification update(Specification item) {
        return null;
    }

    @Override
    public String deleteOne(Specification item) {
        return null;
    }

    @Override
    public void deleteAll(List<Specification> items) {

    }
}
