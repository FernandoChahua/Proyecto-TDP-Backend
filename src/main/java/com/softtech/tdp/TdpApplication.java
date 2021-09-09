package com.softtech.tdp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class TdpApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(TdpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String password = "12345";
		for(int i = 0 ; i< 4;i++) {
			String passwordEncrypted = passwordEncoder.encode(password);
			System.out.println(passwordEncrypted);
		}
	}

	@Bean
	public ModelMapper modelMapper(){ return new ModelMapper(); }

}
