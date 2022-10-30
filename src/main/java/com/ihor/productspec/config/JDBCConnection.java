package com.ihor.productspec.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Slf4j
public class JDBCConnection implements AutoCloseable {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
            log.info("Connection is opened");
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()){
            connection.close();
            log.info("Connection is closed");
        }
    }
}
