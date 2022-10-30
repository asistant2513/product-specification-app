package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.DisentanglementMapper;
import com.ihor.productspec.model.DisentanglementModel;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.INSERT_DISENTANGLEMENT_MODEL;

@Repository
public class DisentanglementRepository {

    private final JdbcTemplate template;

    private final DisentanglementMapper mapper;

    public DisentanglementRepository(final JdbcTemplate template,
                                     final DisentanglementMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public int[] addAll(final List<DisentanglementModel> list) {
        return template.batchUpdate(INSERT_DISENTANGLEMENT_MODEL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                var model = list.get(i);
                ps.setString(1, model.getProductId());
                ps.setString(2, model.getAssemblyId());
                ps.setString(3, model.getComponentId());
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
}
