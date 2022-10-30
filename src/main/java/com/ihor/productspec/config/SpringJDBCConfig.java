package com.ihor.productspec.config;

import com.ihor.productspec.mapping.ComponentMapper;
import com.ihor.productspec.mapping.ProductMapper;
import com.ihor.productspec.mapping.ProductTypeMapper;
import com.ihor.productspec.mapping.SpecificationMapper;
import com.ihor.productspec.model.Component;
import com.ihor.productspec.model.Product;
import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.model.Specification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;

@Configuration
public class SpringJDBCConfig {

    @Bean
    @Qualifier("default-query-mapping")
    public QueryMappingConfiguration configureQueryMapping() {
        return new DefaultQueryMappingConfiguration()
                .registerRowMapper(ProductType.class, new ProductTypeMapper())
                .registerRowMapper(Product.class, new ProductMapper())
                .registerRowMapper(Specification.class, new SpecificationMapper())
                .registerRowMapper(Component.class, new ComponentMapper());
    }
}
