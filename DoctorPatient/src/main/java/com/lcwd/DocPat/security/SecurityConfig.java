package com.lcwd.DocPat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public CustomUserDetailService customService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Autowired
	    private JWTAuthEntryPoint point;
	    @Autowired
	    private JWTAuthenticationFilter filter;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//			.antMatchers(HttpMethod.GET).hasRole("ADMIN")
//			.anyRequest()
//			.authenticated()
//			.and()
//			.httpBasic();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//			.antMatchers("/doctor/home/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("USER").password(passwordEncoder().encode("password1")).roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("password2")).roles("ADMIN");
		
		auth.userDetailsService(customService()).passwordEncoder(passwordEncoder());
	}

}
