package com.ihor.productspec.mapping;

import com.ihor.productspec.model.Product;
import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.model.Specification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SpecificationMapper implements ObjectMapper<Specification>{
    @Override
    public Specification mapOne(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Specification> mapAll(ResultSet resultSet) {
        final List<Specification> specifications = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Specification spec = new Specification();

                Product p1 = new Product();
                ProductType pt1 = new ProductType();
                pt1.setTypeCode(resultSet.getLong("source_type_id"));
                pt1.setTypeName(resultSet.getString("source_type_name"));
                p1.setProductType(pt1);
                p1.setProductCode(resultSet.getString("source_id"));
                p1.setProductName(resultSet.getString("source_name"));

                spec.setSourceProduct(p1);

                Product p2 = new Product();
                ProductType pt2 = new ProductType();
                pt2.setTypeCode(resultSet.getLong("target_type_id"));
                pt2.setTypeName(resultSet.getString("target_type_name"));
                p2.setProductType(pt2);
                p2.setProductCode(resultSet.getString("target_id"));
                p2.setProductName(resultSet.getString("target_name"));

                spec.setTargetProduct(p2);
                spec.setQuantity(resultSet.getLong("quantity"));

                specifications.add(spec);
            }
        }
        catch (SQLException ex) {
            log.error("SQL exception occurred while mapping {} objects: {}", ProductType.class.getName(), ex.getMessage());
        }
        return specifications;
    }
}
