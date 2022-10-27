package com.ihor.productspec;

import com.ihor.productspec.config.DatabaseDataPreparationConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Slf4j
public class ProductSpecApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(ProductSpecApplication.class, args);
		prepareDatabase(context);
	}

	private static void prepareDatabase(final ApplicationContext context){
		try {
			var prop = context.getEnvironment().getProperty("datasource.isReady");
			boolean isDBReady = Boolean.parseBoolean(prop);
			if (!isDBReady) {
				log.info("Database preparation required. Starting DB configuration");
				context.getBean(DatabaseDataPreparationConfig.class).prepareDatabase(context);
			}
		} catch (IOException exception) {
			log.error("Error while preparing database: {}", exception.getMessage());
		}
	}

}
