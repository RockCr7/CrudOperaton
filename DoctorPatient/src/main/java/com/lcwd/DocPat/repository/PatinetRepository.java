package com.lcwd.DocPat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.DocPat.entities.Patient;

public interface PatinetRepository extends JpaRepository<Patient, Long> {

	List<Patient> findBydoc_id(long id);
}
