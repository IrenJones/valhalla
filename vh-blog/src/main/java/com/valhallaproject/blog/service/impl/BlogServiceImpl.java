package com.valhallaproject.blog.service.impl;

import com.valhallaproject.blog.converter.PostConverter;
import com.valhallaproject.blog.converter.PostDtoConverter;
import com.valhallaproject.blog.dto.PostFullDto;
import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.model.Post;
import com.valhallaproject.blog.repository.BlogRepository;
import com.valhallaproject.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private PostConverter postConverter;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private PostDtoConverter dtoConverter;

	@Override
	public PostFullDto addPost(PostShortDto dto) {
		Post post = dtoConverter.convert(dto);
		return postConverter.convert(blogRepository.save(post));
	}

	@Override
	public List<PostFullDto> getAllPosts() {
		return blogRepository.findAll()
				.stream()
				.map(postConverter::convert)
				.collect(Collectors.toList());
	}

	@Override
	public PostFullDto updatePost(Long id, PostShortDto dto) {
		if(blogRepository.existsById(id)){
			Post post = dtoConverter.convert(dto);
			post.setId(id);
			return postConverter.convert(blogRepository.save(post));
		} else {
			throw new EntityNotFoundException("Post id " + id);
		}
	}

	@Override
	public void deletePost(Long id) {
		if(blogRepository.existsById(id)){
			blogRepository.deleteById(id);
			log.info("Deleted post with id=%s", id);
		}
	}
}
