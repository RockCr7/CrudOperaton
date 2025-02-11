package com.lcwd.DocPat.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.DocPat.entities.Doctor;
import com.lcwd.DocPat.exception.ResourseNotFoundException;
import com.lcwd.DocPat.repository.DoctorRepository;
import com.lcwd.DocPat.utils.DoctorResponse;
import com.lcwd.DocPat.utils.Hospital;
import com.lcwd.DocPat.utils.HospitalProxy;

@Service
public class DocServiceImpl {
	
	private static final Logger logger=LoggerFactory.getLogger(DocServiceImpl.class);
	
	@Autowired
	private HospitalProxy hos;
	
	@Autowired
	private RestTemplate restTemp;
	
	@Autowired
	private DoctorRepository docRepo;

	public Doctor createDoc(Doctor doc) {
		return docRepo.save(doc);
	}

	public DoctorResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Doctor> findAllWithPageData = docRepo.findAll(pageable);
		List<Doctor> content = findAllWithPageData.getContent();
		content.stream().map(x->{
			Hospital hospiData = hos.getHospiData(x.getId());
			x.setHos(hospiData);
			return x;
		}).collect(Collectors.toList());
		
		DoctorResponse docResponse = new DoctorResponse();
		docResponse.setContent(content);
		docResponse.setPageNo(findAllWithPageData.getNumber()); // Page class object will give current page no and other
																// build-in methods
		docResponse.setPageSize(findAllWithPageData.getSize());
		docResponse.setTotalPages(findAllWithPageData.getTotalPages());
		docResponse.setTotalElements(findAllWithPageData.getTotalElements());
		docResponse.setLast(findAllWithPageData.isLast());

		return docResponse;
	}
	
	public Doctor findbyid(long id) {
		logger.info("{}",id);
		logger.info("Communication through Rest Template");
		Doctor doc = docRepo.findById(id).orElseThrow(() -> new ResourseNotFoundException("Doctor with this id not found"));
		ResponseEntity<Hospital> forEntity = restTemp.getForEntity("http://localhost:8082/hospital/findByDoc/"+doc.getId(), Hospital.class);
		Hospital body = forEntity.getBody();
		doc.setHos(body);
		return doc;
	}
	
	public Doctor findbyidFeign(long id) {
		logger.info("Communication through Feign Client");
		Doctor doc = docRepo.findById(id).orElseThrow(() -> new ResourseNotFoundException("Doctor with this id not found"));
		Hospital hospiData = hos.getHospiData(doc.getId());
		doc.setHos(hospiData);
		return doc;
	}
}
