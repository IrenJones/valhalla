package com.valhallaproject.runes;

import com.valhallaproject.runes.config.PostgresqlContainer;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunesApplicationTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

	@Test
	public void contextLoads() {
		Assert.assertEquals(postgreSQLContainer.getDatabaseName(), "test");
		Assume.assumeNotNull(postgreSQLContainer);
	}
}
