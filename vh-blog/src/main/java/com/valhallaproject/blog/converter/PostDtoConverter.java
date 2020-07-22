package com.valhallaproject.blog.converter;

import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDtoConverter{

	public <T> Post convert(T postDto) {
		Post post = new Post();
		PostShortDto dto = (PostShortDto) postDto;
		post.setTitle(dto.getTitle());
		post.setDescription(dto.getDescription());
		post.setContent(dto.getContent());
		return post;
	}
}
