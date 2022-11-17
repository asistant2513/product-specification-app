package com.ihor.productspec.mapping;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.model.UsabilityModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsabilityMapper implements RowMapper<UsabilityModel> {
    @Override
    public UsabilityModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsabilityModel model = new UsabilityModel();
        //TODO: implement mapper
        return model;
    }
}
