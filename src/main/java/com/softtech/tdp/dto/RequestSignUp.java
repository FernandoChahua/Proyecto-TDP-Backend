package com.softtech.tdp.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSignUp {
	private String email;
	
	private String password;
	
	private String firstName;

	private String lastName;

	private LocalDate bornDate;
	
	private boolean anonymous;

}
