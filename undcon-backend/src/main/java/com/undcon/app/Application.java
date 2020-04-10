package com.undcon.app;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.undcon.app.multitenancy.DataSourceBeanApplicationListener;
import com.undcon.app.multitenancy.DataSourceProperties;
import com.undcon.app.multitenancy.TenantAwareRoutingSource;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

	public static void main(String... args) {
		new Application()//
				.configure(new SpringApplicationBuilder(Application.class))//
				//
				.run(args);
	}

	
	@Bean(name = "myBean", initMethod = "teste")
	public DataSource dataSource() {
		TenantAwareRoutingSource tenantAwareRoutingSource = new TenantAwareRoutingSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		tenantAwareRoutingSource.setTargetDataSources(targetDataSources);
		tenantAwareRoutingSource.setDefaultTargetDataSource(DataSourceProperties.createDataSource(null));
		return tenantAwareRoutingSource;
	}

	@Bean
	public DataSourceBeanApplicationListener getContextRefreshedListener() {
		return new DataSourceBeanApplicationListener();
	}

	@Bean
	public DataSourceBeanApplicationListener getBootListener() {
		return new DataSourceBeanApplicationListener();
	}

	@Bean
	public CommandLineRunner getRunner(ApplicationContext ctx) {
		return (args) -> {
			ctx.getBean(DataSource.class);
		};
	}

}
