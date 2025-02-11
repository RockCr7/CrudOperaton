package com.lcwd.DocPat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.lcwd.DocPat.entities.User;
import com.lcwd.DocPat.repository.UserRepository;

import brave.sampler.Sampler;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class DoctorPatientApplication  {  //implements CommandLineRunner
//
//	@Autowired
//	private UserRepository userRepo;
//
//	@Autowired
//	private BCryptPasswordEncoder bCrypt;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(bCrypt.encode("password2"));
//	}

}
