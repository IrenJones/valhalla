package com.valhallaproject.runes.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.SQLException;

import static com.valhallaproject.runes.RunesApplicationTest.postgreSQLContainer;
import static java.lang.String.format;

@Configuration
public class DatabaseConfig {

	@Autowired
	private ApplicationContext context;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		// here we reference the static test container variable in our test case to get the used the connection details
		ds.setUrl(format("jdbc:postgresql://%s:%s/%s",
				postgreSQLContainer.getContainerIpAddress(),
				postgreSQLContainer.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
				postgreSQLContainer.getDatabaseName())
		);
		ds.setUsername(postgreSQLContainer.getUsername());
		ds.setPassword(postgreSQLContainer.getPassword());
		ds.setSchema(postgreSQLContainer.getDatabaseName());
		return ds;
	}

	@Bean
	public SpringLiquibase springLiquibase(DataSource dataSource) throws SQLException {
		tryToCreateSchema(dataSource);
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDropFirst(true);
		liquibase.setDataSource(dataSource);
		liquibase.setDefaultSchema("test");
		liquibase.setChangeLog("classpath:/db/changelog/master.xml");

		return liquibase;
	}

	private void tryToCreateSchema(DataSource dataSource) throws SQLException {
		String CREATE_SCHEMA_QUERY = "CREATE SCHEMA IF NOT EXISTS test";
		dataSource.getConnection().createStatement().execute(CREATE_SCHEMA_QUERY);
	}
}
