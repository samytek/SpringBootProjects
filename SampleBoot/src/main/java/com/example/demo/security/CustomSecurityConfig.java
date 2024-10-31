//package com.example.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableMethodSecurity
//public class CustomSecurityConfig {
//
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public UserDetailsService userDetailsService() {
//
//		UserDetails maker = User.withUsername("Sameer").password(bCryptPasswordEncoder().encode("12345"))
//				.roles("NORMAL").build();
//
//		UserDetails admin = User.withUsername("Sameer1").password(bCryptPasswordEncoder().encode("12345"))
//				.roles("NORMAL").build();
//
//		UserDetails checker = User.withUsername("Sameer2").password(bCryptPasswordEncoder().encode("12345"))
//				.roles("ADMIN").build();
//
//		return new InMemoryUserDetailsManager(maker, admin, checker);
//	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests()
////		.requestMatchers("normalUser")
////		.hasRole("NORMAL")
////		.requestMatchers("adminUser")
////		.hasRole("ADMIN")
//		.requestMatchers("home/public").permitAll().anyRequest()
//				.authenticated().and().formLogin();
//
//		return http.build();
//
//	}
//}
