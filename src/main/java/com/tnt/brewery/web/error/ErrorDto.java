package com.tnt.brewery.web.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDto {
	@JsonInclude(value = Include.NON_NULL)
	private String path;
	private String message;
	@JsonInclude(value = Include.NON_NULL)
	private String invalidValue;
}
