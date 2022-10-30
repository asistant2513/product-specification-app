package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.ComponentMapper;
import com.ihor.productspec.model.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID;

@Repository
public class ComponentRepository {

    private final JdbcTemplate template;

    private final ComponentMapper mapper;

    public ComponentRepository(final JdbcTemplate template,
                               final ComponentMapper mapper) {
        this.mapper = mapper;
        this.template = template;
    }

    public List<Component> getAllFirstLevelNodesById(final String id) {
        return template.query(SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID,
                ps -> ps.setString(1, id),
                mapper);
    }

    public List<Component> getAllFirstLevelUsabilityById(String id) {
        return template.query(SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID,
                ps -> ps.setString(1, id),
                mapper);
    }
}
