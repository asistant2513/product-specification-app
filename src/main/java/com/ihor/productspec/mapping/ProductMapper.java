package com.ihor.productspec.mapping;

import com.ihor.productspec.model.Product;
import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductCode(rs.getString("product_id"));
        product.setProductName(rs.getString("product_name"));

        ProductType productType = new ProductType();
        productType.setTypeCode(rs.getLong("type_id"));
        productType.setTypeName(rs.getString("type_name"));

        product.setProductType(productType);

        return product;
    }
}
