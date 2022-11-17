package com.ihor.productspec.mapping;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.model.UsabilityModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsabilityMapper implements RowMapper<UsabilityModel> {
    @Override
    public UsabilityModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsabilityModel model = new UsabilityModel();
        model.setComponentId(rs.getString("component_id"));
        model.setAssemblyId(rs.getString("assembly_id"));
        model.setQuantity(rs.getLong("quantity"));

        ProductType pt = new ProductType();
        pt.setTypeCode(rs.getLong("type_id"));
        pt.setTypeName(rs.getString("type_name"));

        model.setProductType(pt);
        return model;
    }
}
