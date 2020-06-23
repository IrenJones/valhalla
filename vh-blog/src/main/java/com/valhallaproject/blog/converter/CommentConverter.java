package com.valhallaproject.blog.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valhallaproject.blog.dto.CommentDto;
import com.valhallaproject.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements Converter<Comment, CommentDto> {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public CommentDto convert(Comment comment) {
		return mapper.convertValue(comment, CommentDto.class);
	}
}
