package com.ihor.productspec.mapping;

import com.ihor.productspec.model.Product;
import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductMapper implements ObjectMapper<Product> {

    @Override
    public Product mapOne(ResultSet resultSet) {
        Product product = new Product();
        try {
            if(resultSet.next()) {
                product.setProductCode(resultSet.getString("product_id"));
                product.setProductName(resultSet.getString("product_name"));

                ProductType productType = new ProductType();
                productType.setTypeCode(resultSet.getLong("type_id"));
                productType.setTypeName(resultSet.getString("type_name"));

                product.setProductType(productType);
            }
        }
        catch (SQLException ex) {
            log.error("SQL exception occurred while mapping {} objects: {}", ProductType.class.getName(), ex.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> mapAll(ResultSet resultSet) {
        List<Product> products = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Product product = new Product();
                product.setProductCode(resultSet.getString("product_id"));
                product.setProductName(resultSet.getString("product_name"));

                ProductType productType = new ProductType();
                productType.setTypeCode(resultSet.getLong("type_id"));
                productType.setTypeName(resultSet.getString("type_name"));

                product.setProductType(productType);
                products.add(product);
            }
        }
        catch (SQLException ex) {
            log.error("SQL exception occurred while mapping {} objects: {}", ProductType.class.getName(), ex.getMessage());
        }
        return products;
    }
}
