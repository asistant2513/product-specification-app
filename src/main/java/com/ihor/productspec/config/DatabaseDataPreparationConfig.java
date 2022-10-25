package com.ihor.productspec.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Slf4j
public final class DatabaseDataPreparationConfig {
    @Autowired
    private JDBCConnection connection;

    public void prepareDatabase(@NonNull final ApplicationContext context) throws IOException {
        var sqlScripts = context.getResources("classpath:sql/*.sql");

        try (Connection c = connection.getConnection()) {
            for (var script : sqlScripts) {
                String query = readInputStream(script);
                if (!query.equals(Strings.EMPTY)) {
                    c.createStatement().execute(query);
                }
            }
        }
        catch (SQLException exception) {
            log.error("Error during SQL connection : {}", exception.getMessage());
        }
    }

    private String readInputStream(final Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            log.error("IO exception while reading resource '{}': {}", resource.getFilename(), e.getMessage());
            return Strings.EMPTY;
        }
    }
}
