package com.ihor.productspec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ProductSpecApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSpecApplication.class, args);
	}

}
