package com.softtech.tdp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialistRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSpecialistRequest;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private LocalDate bornDate;
	
	private String urlCertificate;
	
	private String password;
	
	private String category;
	
	private LocalDateTime createdAt;
	private SpecialistRequestState state;
	
}
