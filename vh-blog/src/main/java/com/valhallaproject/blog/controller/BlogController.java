package com.valhallaproject.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blog")
@PropertySource("classpath:values.properties")
public class BlogController {

	@Value("${greeting.message}")
	private String greeting;

	@GetMapping(value = "/main")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
}
