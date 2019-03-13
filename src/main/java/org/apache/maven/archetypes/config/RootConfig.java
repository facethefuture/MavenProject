package org.apache.maven.archetypes.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.apache.maven.archetypes.dataSource")
public class RootConfig {
	@Bean
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jdbcdemo");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		dataSource.setInitialSize(10);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(20);
		return dataSource;
	}
	
}
