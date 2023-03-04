package com.carauna.projectmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/api/**")                                   
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().permitAll()
			)
			.csrf().disable()
			.httpBasic();
		return http.build();
	}
}
