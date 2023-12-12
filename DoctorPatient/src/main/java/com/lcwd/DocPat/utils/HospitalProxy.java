package com.lcwd.DocPat.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Hospital-Service",url="localhost:8082")
public interface HospitalProxy {
	
	@GetMapping("/hospital/findByDoc/{id}")
	Hospital getHospiData(@PathVariable long id);

}
