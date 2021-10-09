package com.softtech.tdp.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSignUp {
	
	@Email
	private String email;
	
	@Size(min = 6,message="La contraseña debe ser mínimo de 6 carácteres.")
	private String password;
	
	private String firstName;

	private String lastName;

	private LocalDate bornDate;
	
	private boolean anonymous;

}
