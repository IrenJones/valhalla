package com.valhallaproject.blog.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@PropertySource("classpath:values.properties")
@RunWith(SpringRunner.class)
@WebMvcTest(value = BlogController.class, secure = false)
public class BlogControllerUnitTest {

	@Value("${greeting.message}")
	private String greeting;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private BlogController blogController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGreeting() throws Exception {
		// actual & then
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/blog/main")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		Assert.assertEquals(greeting, content);
	}
}
