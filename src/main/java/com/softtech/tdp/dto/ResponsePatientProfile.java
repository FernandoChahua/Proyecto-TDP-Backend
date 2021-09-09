package com.softtech.tdp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePatientProfile {
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate bornDate;
	private LocalDateTime createdAt;
	
}
