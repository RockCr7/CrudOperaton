package com.lcwd.DocPat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.DocPat.entities.Doctor;
import com.lcwd.DocPat.entities.Patient;
import com.lcwd.DocPat.exception.ResourseNotFoundException;
import com.lcwd.DocPat.repository.DoctorRepository;
import com.lcwd.DocPat.repository.PatinetRepository;

@Service
public class PatServiceImpl {

	@Autowired
	private DoctorRepository docRepo;

	@Autowired
	private PatinetRepository patRepo;

	public Patient createPatient(Patient pat, long id) {
		Doctor doc = docRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Docter with this ID not found"));
		pat.setDoc(doc);
		return patRepo.save(pat);
	}

	public List<Patient> getAllPatientByDocId(long id) {
		return patRepo.findBydoc_id(id);
	}
}
