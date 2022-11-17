package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.UsabilityMapper;
import com.ihor.productspec.model.UsabilityModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_END_LEVEL_NODES_BY;

@Repository
public class UsabilityRepository {

    private final JdbcTemplate template;

    private final UsabilityMapper mapper;

    public UsabilityRepository(JdbcTemplate template, UsabilityMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public List<UsabilityModel> getAllLastLevelNodes() {
        return template.query(SELECT_ALL_END_LEVEL_NODES_BY, mapper);
    }
}
