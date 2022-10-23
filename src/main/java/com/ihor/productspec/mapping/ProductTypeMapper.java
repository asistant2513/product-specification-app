package com.ihor.productspec.mapping;

import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductTypeMapper implements ObjectMapper<ProductType> {

    @Override
    public ProductType mapOne(ResultSet resultSet) {
        final ProductType productType = new ProductType();
        try {
            if(resultSet.next()){
                productType.setTypeCode(resultSet.getLong("type_id"));
                productType.setTypeName(resultSet.getString("type_name"));
            }
        }
        catch (SQLException ex) {
            log.error("SQL exception occurred while mapping {} object: {}", ProductType.class.getName(), ex.getMessage());
            return null;
        }
        return productType;
    }

    @Override
    public List<ProductType> mapAll(ResultSet resultSet) {
        final List<ProductType> productTypes = new ArrayList<>();
        try {
            while(resultSet.next()) {
                ProductType productType = new ProductType();
                productType.setTypeCode(resultSet.getLong("type_id"));
                productType.setTypeName(resultSet.getString("type_name"));
                productTypes.add(productType);
            }
        }
        catch (SQLException ex) {
            log.error("SQL exception occurred while mapping {} objects: {}", ProductType.class.getName(), ex.getMessage());
        }
        return productTypes;
    }
}
