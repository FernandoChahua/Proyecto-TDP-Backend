package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.softtech.tdp.dto.RequestSignUp;
import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.model.ConfirmationToken;
import com.softtech.tdp.repository.AppUserRepository;
import com.softtech.tdp.service.IPatientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements UserDetailsService {

	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenServiceImpl confirmationTokenService;
	private final IPatientService patientService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

		if (userExists) {
			throw new IllegalStateException("El email ya existe");
		}

		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

		appUser.setPassword(encodedPassword);

		appUserRepository.save(appUser);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), appUser);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

//        TODO: SEND EMAIL

		return token;
	}
	public RequestSignUp signUpPatient(RequestSignUp request) {
	
		boolean userExists = appUserRepository.findByEmail(request.getEmail()).isPresent();

		if (userExists) {
			throw new IllegalStateException("El email ya existe");
		}
		
		
	}

	public int enableAppUser(String email) {
		return appUserRepository.enableAppUser(email);
	}
}
