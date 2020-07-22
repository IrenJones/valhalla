package com.valhallaproject.blog.utils;

import com.valhallaproject.blog.dto.PostFullDto;
import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.model.Post;
import lombok.experimental.UtilityClass;

import java.util.Collections;

@UtilityClass
public class PostCreatingUtils {

	public PostShortDto createPostShortDto(){
		PostShortDto dto = new PostShortDto();
		dto.setTitle("NEWS");
		dto.setDescription("Fantastic news from Finland!");
		dto.setContent("Hard Rock Hallelujah!");
		return dto;
	}

	public PostShortDto createPostShortDtoWithNullTitle(){
		PostShortDto dto = new PostShortDto();
		dto.setDescription("Fantastic news from Finland!");
		dto.setContent("Hard Rock Hallelujah!");
		return dto;
	}

	public PostFullDto createPostFullDto(){
		PostFullDto dto = new PostFullDto();
		dto.setTitle("NEWS");
		dto.setDescription("Fantastic news from Finland!");
		dto.setContent("Hard Rock Hallelujah!");
		dto.setComments(Collections.EMPTY_LIST);
		return dto;
	}

	public Post createPost(Long id, String title){
		Post post = new Post();
		post.setId(id);
		post.setTitle(title);
		post.setDescription("Fantastic news from Finland!");
		post.setContent("Hard Rock Hallelujah!");
		post.setComments(Collections.EMPTY_LIST);
		return post;
	}
}
