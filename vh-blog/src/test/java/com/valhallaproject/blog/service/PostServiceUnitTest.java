package com.valhallaproject.blog.service;

import com.valhallaproject.blog.converter.PostConverter;
import com.valhallaproject.blog.converter.PostDtoConverter;
import com.valhallaproject.blog.dto.CommentDto;
import com.valhallaproject.blog.dto.PostFullDto;
import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.model.Post;
import com.valhallaproject.blog.repository.PostRepository;
import com.valhallaproject.blog.service.impl.PostServiceImpl;
import com.valhallaproject.blog.utils.PostCreatingUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PostServiceUnitTest {

	@Mock
	private PostRepository postRepository;

	@Mock
	private PostDtoConverter dtoConverter;

	@Mock
	private PostConverter converter;

	@InjectMocks
	private PostService postService = new PostServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void successfulCreationWhenParametersValid(){
		// given
		Post post = PostCreatingUtils.createPost(1L, "NEWS");
		PostFullDto dto = PostCreatingUtils.createPostFullDto();
		PostShortDto inputDto = PostCreatingUtils.createPostShortDto();

		// when
		when(dtoConverter.convert(any(PostShortDto.class))).thenReturn(post);
		when(postRepository.save(any(Post.class))).thenReturn(post);
		when(converter.convert(any(Post.class))).thenReturn(dto);

		// action
		PostFullDto result = postService.addPost(inputDto);

		// then
		assertThat(result, allOf(
				hasProperty("title", equalTo("NEWS")),
				hasProperty("description", equalTo("Fantastic news from Finland!")),
				hasProperty("content", equalTo("Hard Rock Hallelujah!")),
				hasProperty("comments", emptyCollectionOf(CommentDto.class))
		));
	}
}
