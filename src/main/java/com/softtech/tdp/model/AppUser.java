package com.softtech.tdp.model;



import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	private Boolean locked = false;
	private Boolean enabled = false;
	
	private Boolean online = false;

	public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	public String getPassword() {
		return password;
	}


	public String getUsername() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isAccountNonExpired() {
		return true;
	}


	public boolean isAccountNonLocked() {
		return !locked;
	}


	public boolean isCredentialsNonExpired() {
		return true;
	}


	public boolean isEnabled() {
		return enabled;
	}

}
