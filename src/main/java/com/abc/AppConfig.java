package com.abc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {
 
	@Bean(name = "system1")
	@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource system1DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplate1")
	public JdbcTemplate jdbcTemplate1(@Qualifier("system1") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean(name = "system2")
	@ConfigurationProperties(prefix = "spring.system2-db")
	public DataSource system2DataSource() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplate2")
	public JdbcTemplate jdbcTemplate2(@Qualifier("system2") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
