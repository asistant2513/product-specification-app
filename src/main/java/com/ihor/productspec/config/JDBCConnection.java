package com.ihor.productspec.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@Scope("session")
@Slf4j
public final class JDBCConnection {

    private final String url;

    private final String username;

    private final String password;

    private final long connectionTTL;

    private long connectionIssuedTimestamp;

    private Connection connection;

    public JDBCConnection(@Value("${datasource.url}") String url,
                          @Value("${datasource.username}") String username,
                          @Value("${datasource.password}") String password,
                          @Value("${datasource.connection.batchExecution.ttl:30}") long connectionTTL) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.connectionTTL = connectionTTL;
    }

    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
            connectionIssuedTimestamp = System.currentTimeMillis();
            log.info("Connection for {} opened at {}", Thread.currentThread().getName(), connectionIssuedTimestamp);
        }
        return connection;
    }

    public ResultSet executeQuery(@NonNull final String sql) throws SQLException{
        try (var conn = getConnection()) {
            try (var statement = conn.createStatement()) {
                return statement.executeQuery(sql);
            }
        }
    }

    public PreparedStatement getPreparedStatement(@NonNull final String sql) throws SQLException{
        return getConnection().prepareStatement(sql);
    }

    public ResultSet executePreparedQuery(@NonNull final PreparedStatement statement) throws SQLException{
        try (statement){
            return statement.executeQuery();
        } finally {
            if(!connection.isClosed()) {
                connection.close();
            }
        }
    }
}
