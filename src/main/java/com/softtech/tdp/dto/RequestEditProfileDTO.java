package com.softtech.tdp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestEditProfileDTO {
	private String firstName;
	private String lastName;
	private LocalDate bornDate;
}
