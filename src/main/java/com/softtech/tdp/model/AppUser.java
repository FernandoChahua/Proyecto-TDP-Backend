package com.softtech.tdp.model;



import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AppUser implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String email;
	
	@JsonIgnore
	private String password;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	
	
	private Boolean locked;
	
	private Boolean enabled;
	
	private Boolean online;


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

	public AppUser(String email, String password, AppUserRole appUserRole) {
		super();
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

}
