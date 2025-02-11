package com.lcwd.DocPat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.DocPat.entities.Doctor;
import com.lcwd.DocPat.service.DocServiceImpl;
import com.lcwd.DocPat.utils.AppConstants;
import com.lcwd.DocPat.utils.DoctorResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DocServiceImpl docService;

	@PostMapping
	public ResponseEntity<Object> createDocData(@Valid @RequestBody Doctor doc, BindingResult binRes) {

		if (binRes.hasErrors()) {
			return new ResponseEntity<>(binRes.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(docService.createDoc(doc), HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DoctorResponse> getAll(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.Default_Page_No, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.Default_Page_Size, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.Default_Sort_By, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.Default_Sort_Dir, required = false) String sortDir) {

		return ResponseEntity.status(HttpStatus.OK).body(docService.getAll(pageNo, pageSize, sortBy, sortDir));

	}

	// Rest-Template implementation
	@GetMapping("/{id}")
	@CircuitBreaker(name = "DocPatBreaker", fallbackMethod = "DocPatFallBack")
	public ResponseEntity<EntityModel<Doctor>> getDocById(@PathVariable long id) {
		Doctor findbyid = docService.findbyid(id);
		EntityModel<Doctor> entMod = EntityModel.of(findbyid);
		WebMvcLinkBuilder link = linkTo(methodOn(DoctorController.class).getAll(0, 5, AppConstants.Default_Sort_By,
				AppConstants.Default_Sort_Dir));
		entMod.add(link.withRel("all-users"));
		return ResponseEntity.status(HttpStatus.OK).body(entMod);
	}

	// FallBack Method
	public ResponseEntity<Doctor> DocPatFallBack(long id, Exception ex) {
		Doctor doc = new Doctor();
		doc.setName(" Some Microservice is down");
		doc.setSpecialization("Dummy");
		doc.setMobile("xxxxxxxxxx");
		return ResponseEntity.status(HttpStatus.OK).body(doc);
	}

	@GetMapping("/feign/{id}")
	public ResponseEntity<Doctor> getDocByIdFeign(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.OK).body(docService.findbyidFeign(id));
	}
	
	// This is to demonstrate antMatchers
	@GetMapping("/home")
	public String demonstrateAntMatchers() {
		return "This is to demonstrate ant matchers";
	}
}
