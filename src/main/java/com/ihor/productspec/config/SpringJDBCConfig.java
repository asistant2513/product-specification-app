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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringJDBCConfig {

    @Bean
    public DataSource configureDataSource(@Value("${spring.datasource.url}") final String url,
                                          @Value("${spring.datasource.username}") final String user,
                                          @Value("${spring.datasource.password}") final String pwd,
                                          @Value("${spring.datasource.driver-class-name}") final String driver){
        DriverManagerDataSource manager = new DriverManagerDataSource();
        manager.setUrl(url);
        manager.setUsername(user);
        manager.setPassword(pwd);
        manager.setDriverClassName(driver);
        return manager;
    }

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
