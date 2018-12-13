package com.qa.cv_manager.userapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class MvcConfig {

	@Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    	driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	driverManagerDataSource.setUrl("${spring.datasource.url}");
    	driverManagerDataSource.setUsername("${spring.datasource.username}");
    	driverManagerDataSource.setPassword("${spring.datasource.password}");
    	return driverManagerDataSource;
    }
}
