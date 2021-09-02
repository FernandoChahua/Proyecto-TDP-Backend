package com.softtech.tdp.security.config;

import java.util.Arrays;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.softtech.tdp.error.CustomAccessDeniedHandler;
import com.softtech.tdp.error.CustomAuthenticationEntryPoint;
import com.softtech.tdp.service.impl.AppUserServiceImpl;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

   private final AppUserServiceImpl userDetailsService;
   
   private final PasswordEncoder passwordEncoder;
   
   private final AuthenticationEventPublisher authenticationEventPublisher;

   
   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setPasswordEncoder(passwordEncoder);
       provider.setUserDetailsService(userDetailsService);
       return provider;
   }

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   http.csrf().disable();
	   http.httpBasic().disable();
       http
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
               .antMatchers("/user/**").permitAll()
               .antMatchers("/oauth/**").permitAll()
              // .antMatchers("/api/glee/**").hasAnyAuthority("ADMIN", "USER")
               //.antMatchers("/api/users/**").hasAuthority("ADMIN")
               //.antMatchers("/api/**").authenticated()
               .anyRequest().authenticated()
               .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler())
               .and().cors().configurationSource(corsConfigurationSource());
   }
   
   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
	   CorsConfiguration corsConfig = new CorsConfiguration();
	   corsConfig.setAllowedOrigins(Arrays.asList("*"));
	   corsConfig.setAllowedMethods(Arrays.asList("POST","PUT","DELETE","GET","OPTIONS"));
	   corsConfig.setAllowCredentials(true);
	   corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
	   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   source.registerCorsConfiguration("/**",corsConfig);
	   
	   return source;
   }
   @Bean
   public FilterRegistrationBean<CorsFilter> corsFilter(){
	   FilterRegistrationBean<CorsFilter> bean =  new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
	   bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	   return bean;
   }

}
