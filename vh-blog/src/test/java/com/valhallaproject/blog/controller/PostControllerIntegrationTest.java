package com.valhallaproject.blog.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

	@Autowired
	private PostController postController;

	@Test
	public void whenAddNewPostReturnPost() throws Exception {
		PostShortDto dto = PostCreatingUtils.createPostShortDto();

		ResponseEntity<PostFullDto> result = postController.addPost(dto);
		assertThat(result.getBody(), allOf(
				hasProperty("title", equalTo("NEWS")),
				hasProperty("description", equalTo("Fantastic news from Finland!")),
				hasProperty("content", equalTo("Hard Rock Hallelujah!")),
				hasProperty("comments", emptyCollectionOf(CommentDto.class))
		));
	}

	@Test(expected = Exception.class)
	public void whenAddNewPostWithNullFieldReturnError() throws Exception {
		PostShortDto dto = PostCreatingUtils.createPostShortDtoWithNullTitle();
		postController.addPost(dto);
	}
}
