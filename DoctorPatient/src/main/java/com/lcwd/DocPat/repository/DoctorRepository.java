package com.lcwd.DocPat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.DocPat.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
