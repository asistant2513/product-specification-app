package com.ihor.productspec.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

@Component
@Slf4j
public final class DatabaseDataPreparationConfig {
    @Autowired
    private JDBCConnection connection;

    public void prepareDatabase(@NonNull final ApplicationContext context) throws IOException {
        var sqlScripts = context.getResources("classpath:sql/*.sql");

        try (Connection c = connection.getConnection()) {
            for (var script : sqlScripts) {
                String query = new String(script.getInputStream().readAllBytes());
                c.createStatement().execute(query);
            }
        }
        catch (SQLException exception) {
            log.error("Error during SQL connection : {}", exception.getMessage());
        }
    }
}
