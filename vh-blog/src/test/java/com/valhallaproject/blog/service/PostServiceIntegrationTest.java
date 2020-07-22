package com.valhallaproject.blog.service;

import com.valhallaproject.blog.config.PostgresqlContainer;
import com.valhallaproject.blog.dto.CommentDto;
import com.valhallaproject.blog.dto.PostFullDto;
import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.utils.PostCreatingUtils;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceIntegrationTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

	@Autowired
	private PostService postService;

	@Test
	public void successfulCreationWhenParametersValid(){
		// given & when
		PostShortDto dto = PostCreatingUtils.createPostShortDto();

		// action
		PostFullDto result = postService.addPost(dto);

		// then
		assertThat(result, allOf(
				hasProperty("title", equalTo("NEWS")),
				hasProperty("description", equalTo("Fantastic news from Finland!")),
				hasProperty("content", equalTo("Hard Rock Hallelujah!")),
				hasProperty("comments", emptyCollectionOf(CommentDto.class))
		));
	}
}
