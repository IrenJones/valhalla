package com.valhallaproject.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostShortDto {

	@NotNull
	@ApiModelProperty(example = "New Title", required = true)
	private String title;

	@NotNull
	@ApiModelProperty(example = "New Description", required = true)
	private String description;

	@NotNull
	@ApiModelProperty(example = "Add something very interesting", required = true)
	private String content;
}
