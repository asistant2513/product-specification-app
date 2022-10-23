package com.ihor.productspec.repository;

import com.ihor.productspec.config.JDBCConnection;
import com.ihor.productspec.mapping.ComponentMapper;
import com.ihor.productspec.model.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID;

@Repository
@Slf4j
public class ComponentRepository implements SQLiteComplexRepository <Component, String> {

    @Autowired
    private JDBCConnection connection;

    @Autowired
    private ComponentMapper mapper;

    @Override
    public List<Component> getAllFirstLevelNodesById(String id) {
        return getResults(String.format(SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID, id));
    }

    @Override
    public List<Component> getAllFirstLevelUsabilityById(String id) {
        return getResults(String.format(SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID, id));
    }

    private List<Component> getResults(final String query) {
        try (Connection c = connection.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            return mapper.mapAll(rs);
        }
        catch (SQLException ex) {
            log.error("Exception occurred during connection: {}", ex.getMessage());
            return null;
        }
    }
}
