package com.ihor.productspec.mapping;

import com.ihor.productspec.model.Component;
import com.ihor.productspec.model.ProductType;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
@Slf4j
public class ComponentMapper implements ObjectMapper<Component> {
    @Override
    public Component mapOne(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<Component> mapAll(ResultSet resultSet) {
        final List<Component> productTypes = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Component component = new Component();
                component.setComponentId(resultSet.getString("component_id"));
                component.setComponentName(resultSet.getString("component_name"));
                component.setQuantity(resultSet.getLong("quantity"));
                productTypes.add(component);
            }
        }
        catch (SQLException ex) {
            log.error("SQL exception occurred while mapping {} objects: {}", ProductType.class.getName(), ex.getMessage());
        }
        return productTypes;
    }
}
