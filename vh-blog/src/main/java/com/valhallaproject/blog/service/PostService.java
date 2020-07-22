package com.valhallaproject.blog.service;

import com.valhallaproject.blog.dto.PostShortDto;
import com.valhallaproject.blog.dto.PostFullDto;

import java.util.List;

public interface PostService {

	PostFullDto addPost(PostShortDto dto);

	List<PostFullDto> getAllPosts();

	PostFullDto updatePost(Long id, PostShortDto dto);

	void deletePost(Long id);
}
