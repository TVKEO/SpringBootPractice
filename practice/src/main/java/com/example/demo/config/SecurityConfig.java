package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers("/admin/signup", "/admin/signin").permitAll()
						.requestMatchers("/admin/contacts/**").authenticated()
						.requestMatchers("/admin/**").authenticated())
				.formLogin(formLogin -> formLogin
						.loginPage("/admin/signin")
						.loginProcessingUrl("/admin/signin")
						.defaultSuccessUrl("/admin/contacts", true)
						.permitAll())
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
						.logoutSuccessUrl("/admin/signin")
						.permitAll())
				.exceptionHandling(exceptionHandling -> exceptionHandling
						.accessDeniedPage("/access-denied")
						.csrf(csrf -> csrf)
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
		return http.build();
	}
}