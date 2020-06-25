package com.valhallaproject.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valhallaproject.blog.dto.PostFullDto;
import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.model.Post;
import com.valhallaproject.blog.service.PostService;
import com.valhallaproject.blog.service.impl.PostServiceImpl;
import com.valhallaproject.blog.utils.PostCreatingUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class, secure = false)
public class PostControllerUnitTest {

	@MockBean
	private PostService postService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private PostController postController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenAddNewPostReturnPost() throws Exception {
		// given
		ObjectMapper mapper = new ObjectMapper();
		PostShortDto dto = PostCreatingUtils.createPostShortDto();
		PostFullDto post = PostCreatingUtils.createPostFullDto();

		// when
		when(postService.addPost(any(PostShortDto.class))).thenReturn(post);

		// actual & then
		mockMvc.perform(MockMvcRequestBuilders.post("/blog/posts/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.title", is("NEWS")))
				.andExpect(jsonPath("$.description", is("Fantastic news from Finland!")))
				.andExpect(jsonPath("$.content", is("Hard Rock Hallelujah!")))
				.andDo(print());
	}

	@Test
	public void whenAddNewPostWithNullFieldReturnError() throws Exception {
		// given
		ObjectMapper mapper = new ObjectMapper();
		PostShortDto dto = PostCreatingUtils.createPostShortDtoWithNullTitle();

		// when

		// actual & then
		mockMvc.perform(MockMvcRequestBuilders.post("/blog/posts/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest());
	}
}
