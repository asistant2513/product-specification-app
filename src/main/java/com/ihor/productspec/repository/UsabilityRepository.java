package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.DisentanglementMapper;
import com.ihor.productspec.mapping.TotalDisentanglementMapper;
import com.ihor.productspec.model.DisentanglementModel;
import com.ihor.productspec.model.TotalDisentanglementModel;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.*;

@Repository
public class UsabilityRepository {

    private final JdbcTemplate template;

    private final DisentanglementMapper mapper;

    private final TotalDisentanglementMapper totalDisentanglementMapper;

    public UsabilityRepository( final JdbcTemplate template,
                                final DisentanglementMapper mapper,
                                final TotalDisentanglementMapper totalDisentanglementMapper ) {
        this.template = template;
        this.mapper = mapper;
        this.totalDisentanglementMapper = totalDisentanglementMapper;
    }

    public List<DisentanglementModel> getStructuralUsability() {
        return template.query(SELECT_STRUCTURAL_USABILITY, mapper);
    }

    public List<TotalDisentanglementModel> getTotalUsability() {
        return template.query(SELECT_TOTAL_USABILITY_RECORDS, totalDisentanglementMapper);
    }

    public int[] addAll(final List<DisentanglementModel> list) {

        deleteAll();
        return template.batchUpdate(INSERT_USABILITY_MODELS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues( PreparedStatement ps, int i) throws SQLException
            {
                var model = list.get(i);
                ps.setString(1, model.getProductId());
                ps.setString(2, model.getComponentId());
                ps.setString(3, model.getAssemblyId());
                ps.setLong(4, model.getQuantity());
                ps.setInt(5, model.getNodeLevel());
                ps.setString(6, model.getTreeNodeLevel());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    private int deleteAll()
    {
        return template.update(DELETE_ALL_STRUCTURAL_USABILITY_RECORDS);
    }
}
