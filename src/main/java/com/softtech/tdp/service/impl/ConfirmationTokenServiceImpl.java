package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softtech.tdp.model.ConfirmationToken;
import com.softtech.tdp.repository.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl {
	 private final ConfirmationTokenRepository confirmationTokenRepository;

	    public void saveConfirmationToken(ConfirmationToken token) {
	        confirmationTokenRepository.save(token);
	    }

	    public Optional<ConfirmationToken> getToken(String token) {
	        return confirmationTokenRepository.findByToken(token);
	    }

	    public int setConfirmedAt(String token) {
	        return confirmationTokenRepository.updateConfirmedAt(
	                token, LocalDateTime.now());
	    }
}
