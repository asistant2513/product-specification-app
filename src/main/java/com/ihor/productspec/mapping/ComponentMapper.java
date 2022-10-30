package com.ihor.productspec.mapping;

import com.ihor.productspec.model.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@org.springframework.stereotype.Component
@Slf4j
public class ComponentMapper implements RowMapper<Component> {
    @Override
    public Component mapRow(ResultSet rs, int rowNum) throws SQLException {
        Component component = new Component();
        component.setComponentId(rs.getString("component_id"));
        component.setComponentName(rs.getString("component_name"));
        component.setQuantity(rs.getLong("quantity"));
        return component;
    }
}
