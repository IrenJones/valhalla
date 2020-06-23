package com.valhallaproject.blog.repository;

import com.valhallaproject.blog.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Post, Long> {
	List<Post> findAll();
}
