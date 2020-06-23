package com.valhallaproject.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

	@NotNull
	@ApiModelProperty(example = "Your nickname", required = true)
	private String nickName;

	@NotNull
	@ApiModelProperty(example = "Add something very interesting", required = true)
	private String content;
}
