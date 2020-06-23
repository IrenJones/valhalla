package com.valhallaproject.blog;

import com.valhallaproject.blog.config.PostgresqlContainer;
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
public class BlogApplicationTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

	@Test
	public void contextLoads() {
		Assume.assumeNotNull(postgreSQLContainer);
	}
}
