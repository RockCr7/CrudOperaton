package com.lcwd.hospitalService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hospitalService.entity.Hospital;
import com.lcwd.hospitalService.repository.HospitalRepository;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	private static final Logger logger=LoggerFactory.getLogger(HospitalController.class);
	@Autowired
	HospitalRepository hosRepo;

	@GetMapping("/findByDoc/{id}")
	public Hospital getHosByDocId(@PathVariable long id) {
		logger.info("Inside Hospital Controller and recieved id : {}",id);
		return hosRepo.findByDoc(id);
	}
	
}
