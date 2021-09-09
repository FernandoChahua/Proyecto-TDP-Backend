package com.softtech.tdp.security.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.model.AppUserRole;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.model.Specialist;
import com.softtech.tdp.service.IPatientService;
import com.softtech.tdp.service.ISpecialistService;
import com.softtech.tdp.service.impl.AppUserServiceImpl;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private AppUserServiceImpl userService;
	
	@Autowired
	private ISpecialistService specialistService;
	
	@Autowired
	private IPatientService patientService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("prueba", "12345");
		
		AppUser user = userService.findByEmail(authentication.getName());
		info.put("userId", user.getId().toString());
		if(user.getAppUserRole() == AppUserRole.SPECIALIST) {
			Specialist specialist = specialistService.findByUserId(user.getId());
			info.put("specialistId",specialist.getIdSpecialist().toString());
		}
		
		if(user.getAppUserRole() == AppUserRole.PATIENT) {
			Patient patient = patientService.findByUserId(user.getId());
			info.put("patientId",patient.getIdPatient().toString());
		}
			
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
	
	
}
