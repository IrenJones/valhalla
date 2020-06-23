package com.valhallaproject.runes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@TestPropertySource(locations = {"classpath:application.yaml" })
public class PostgresqlContainer extends PostgreSQLContainer<PostgresqlContainer> {
	private static final String IMAGE_VERSION = "postgres:11.1";
	private static PostgresqlContainer container;

	@Value("${dbName}")
	private String dbName;

	private PostgresqlContainer() {
		super(IMAGE_VERSION);
	}

	public static PostgresqlContainer getInstance() {
		if (container == null) {
			container = new PostgresqlContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("DB_URL", container.getJdbcUrl());
		System.setProperty("DB_USERNAME", container.getUsername());
		System.setProperty("DB_PASSWORD", container.getPassword());
	}

	@Override
	public void stop() {
		//do nothing, JVM handles shut down
	}
}
