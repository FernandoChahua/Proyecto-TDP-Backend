package com.softtech.tdp.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

   private final AuthenticationManager authenticationManager;

   private final BCryptPasswordEncoder passwordEncoder;

   private final UserDetailsService userService;
   
   private final InfoAdicionalToken infoAdicionalToken;

   
   public OAuthConfiguration(AuthenticationManager authenticationManager,BCryptPasswordEncoder passwordEncoder,
		UserDetailsService userService, InfoAdicionalToken infoAdicionalToken) {
	super();
	this.authenticationManager = authenticationManager;
	this.passwordEncoder = passwordEncoder;
	this.userService = userService;
	this.infoAdicionalToken = infoAdicionalToken;
}
@Value("${jwt.clientId:flutter-app}")
   private String clientId;

   @Value("${jwt.client-secret:12345}")
   private String clientSecret;

   @Value("${jwt.signing-key:123}")
   private String jwtSigningKey;

   @Value("${jwt.accessTokenValidititySeconds:43200}") // 12 hours
   private int accessTokenValiditySeconds;

   @Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
   private String[] authorizedGrantTypes;

   @Value("${jwt.refreshTokenValiditySeconds:2592000}") // 30 days
   private int refreshTokenValiditySeconds;

  

   
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
	   security.tokenKeyAccess("permitAll()")
	   .checkTokenAccess("isAuthenticated()")
	   .allowFormAuthenticationForClients();
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.inMemory()
               .withClient(clientId)
               .secret(passwordEncoder.encode(clientSecret))
               .accessTokenValiditySeconds(accessTokenValiditySeconds)
               .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
               .authorizedGrantTypes(authorizedGrantTypes)
               .scopes("read", "write")
               /*.and()
               .withClient(clientId)
               .secret(passwordEncoder.encode(clientSecret))
               .accessTokenValiditySeconds(accessTokenValiditySeconds)
               .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
               .authorizedGrantTypes(authorizedGrantTypes)
               .scopes("read", "write")
               .resourceIds("api")*/;
   }

   @Override
   public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
	   TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	   tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
	   
       endpoints
       		   .tokenStore(tokenStore())
               .accessTokenConverter(accessTokenConverter())
               .tokenEnhancer(tokenEnhancerChain)
               .userDetailsService(userService)
               .authenticationManager(authenticationManager);
               ;
   }

   @Bean
   public JwtAccessTokenConverter accessTokenConverter() {
       JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
       converter.setSigningKey(jwtSigningKey);
       return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() {
	   return new JwtTokenStore(accessTokenConverter());
   }
}