package com.softtech.tdp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialist implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSpecialist;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private LocalDate bornDate;
	
	private String urlCertificate;
	
	private boolean state;
	
	@OneToMany(mappedBy = "specialist", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Feedback> feedbacks;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private AppUser user;
	
}
