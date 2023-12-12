package com.lcwd.DocPat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.DocPat.entities.Patient;
import com.lcwd.DocPat.service.PatServiceImpl;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatServiceImpl patServiceImpl;

	@PostMapping("/post/{id}")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient, @PathVariable("id") long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(patServiceImpl.createPatient(patient, id));
	}

	// Get Patient by doctor id

	@GetMapping("/getByDocId/{id}")
	public ResponseEntity<List<Patient>> getAllPatientByDocId(@PathVariable("id") long id) {
		return ResponseEntity.status(HttpStatus.OK).body(patServiceImpl.getAllPatientByDocId(id));
	}
}
