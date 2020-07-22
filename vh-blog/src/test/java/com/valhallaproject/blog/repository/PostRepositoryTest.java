package com.valhallaproject.blog.repository;

import com.valhallaproject.blog.BlogApplication;
import com.valhallaproject.blog.config.DatabaseConfig;
import com.valhallaproject.blog.config.PostgresqlContainer;
import com.valhallaproject.blog.dto.CommentDto;
import com.valhallaproject.blog.model.Post;
import com.valhallaproject.blog.utils.PostCreatingUtils;
import junit.framework.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class PostRepositoryTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

	@Autowired
	private PostRepository postRepository;

	@Test
	@Transactional
	public void contextLoads() {
		Post post = PostCreatingUtils.createPost(1L, "wohoo");
		postRepository.save(post);
		Optional<Post> result = postRepository.findById(1L);

		assertTrue(result.isPresent());
		assertThat(result.get(), allOf(
				hasProperty("id", equalTo(1L)),
				hasProperty("title", equalTo("wohoo")),
				hasProperty("description", equalTo("Fantastic news from Finland!")),
				hasProperty("content", equalTo("Hard Rock Hallelujah!")),
				hasProperty("comments", emptyCollectionOf(CommentDto.class))
		));
	}
}
