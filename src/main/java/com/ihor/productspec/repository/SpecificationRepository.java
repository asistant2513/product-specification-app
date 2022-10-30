package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.SpecificationMapper;
import com.ihor.productspec.model.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_SPECS;
import static com.ihor.productspec.util.JDBCConstants.ADD_SPEC_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_SPEC_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_SPEC_RECORD;

@Repository
public class SpecificationRepository {

    private final JdbcTemplate template;

    private final SpecificationMapper mapper;

    public SpecificationRepository(final JdbcTemplate template,
                                   final SpecificationMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public List<Specification> getAll() {
        return template.query(SELECT_ALL_SPECS, mapper);
    }

    public int addOne(final String sourceProductId,
               final String targetProductId,
               long quantity) {
        return template.update(ADD_SPEC_RECORD, ps -> {
            ps.setString(1, sourceProductId);
            ps.setString(2, targetProductId);
            ps.setLong(3, quantity);
        });
    }

    public int update(final String sourceProductId,
               final String targetProductId,
               long quantity) {
        return template.update(UPDATE_SPEC_RECORD, ps -> {
            ps.setLong(1, quantity);
            ps.setString(2, sourceProductId);
            ps.setString(3, targetProductId);
        });
    }

    public int deleteOne(final Specification item) {
        return template.update(DELETE_SPEC_RECORD, ps -> {
            ps.setString(1, item.getSourceProduct().getProductCode());
            ps.setString(2, item.getTargetProduct().getProductCode());
        });
    }
}
