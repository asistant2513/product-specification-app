package com.ihor.productspec.mapping;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.model.TotalDisentanglementModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TotalDisentanglementMapper implements RowMapper<TotalDisentanglementModel> {

    @Override
    public TotalDisentanglementModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        var model = new TotalDisentanglementModel();
        model.setProductId(rs.getString("product_id"));
        model.setComponentId(rs.getString("component_id"));
        model.setTotalQuantity(rs.getLong("total_component_quantity"));
        model.setMaxNodeLevel(rs.getInt("max_development_level"));

        var productType = new ProductType();
        productType.setTypeCode(rs.getLong("product_type_id"));
        productType.setTypeName(rs.getString("type_name"));

        model.setProductType(productType);

        return model;
    }
}
