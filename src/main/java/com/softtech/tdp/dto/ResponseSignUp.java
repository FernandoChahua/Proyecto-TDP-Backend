package com.softtech.tdp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseSignUp {
	private String message;
	private Integer statusCode;
}
