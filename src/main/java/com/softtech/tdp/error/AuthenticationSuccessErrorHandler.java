package com.softtech.tdp.error;

import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher{

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		// TODO Auto-generated method stub
		if( authentication.getDetails() instanceof WebAuthenticationDetails) return;
		
		System.out.println("Success Login" + authentication.getName());
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		// TODO Auto-generated method stub
		if( authentication.getDetails() instanceof WebAuthenticationDetails) return;
		System.out.println("Error en login : "+ exception.getMessage());
	}
	
}
