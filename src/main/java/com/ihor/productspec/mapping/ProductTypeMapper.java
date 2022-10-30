package com.ihor.productspec.mapping;

import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class ProductTypeMapper implements RowMapper<ProductType> {

    @Override
    public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
        final ProductType productType = new ProductType();
        productType.setTypeCode(rs.getLong("type_id"));
        productType.setTypeName(rs.getString("type_name"));
        return productType;
    }
}
