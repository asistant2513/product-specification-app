package com.ihor.productspec.mapping;

import com.ihor.productspec.model.DisentanglementModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DisentanglementMapper implements RowMapper<DisentanglementModel> {
    @Override
    public DisentanglementModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        DisentanglementModel model = new DisentanglementModel();

        model.setProductId(rs.getString("product_id"));
        model.setAssemblyId(rs.getString("assembly_id"));
        model.setComponentId(rs.getString("component_id"));
        model.setQuantity(rs.getLong("quantity"));
        model.setNodeLevel(rs.getInt("development_level"));
        model.setTreeNodeLevel(rs.getString("tree_level"));

        return model;
    }
}
