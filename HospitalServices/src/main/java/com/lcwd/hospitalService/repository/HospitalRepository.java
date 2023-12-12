package com.lcwd.hospitalService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lcwd.hospitalService.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{
	
	@Query("SELECT h FROM Hospital h WHERE h.Doc = :doc")
	Hospital findByDoc(@Param("doc") long id);
}
