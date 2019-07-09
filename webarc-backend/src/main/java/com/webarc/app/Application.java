package com.webarc.app;

import java.sql.SQLException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String... args) {
    	try {
			DatabaseMigration.migrateAllSchemas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        new SpringApplicationBuilder()
                .sources(Application.class)
                .showBanner(false)
                .run(args);
    }

}
