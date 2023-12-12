package com.lcwd.DocPat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.DocPat.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);
}
