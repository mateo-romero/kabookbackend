package com.kabook.kabook.controller;

import com.kabook.kabook.model.Policy;
import com.kabook.kabook.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
@CrossOrigin
public class PolicyController {

	@Autowired
	IPolicyService policyService;

	@GetMapping
	public List<Policy> findAllPolicies() {
		return policyService.findAllPolicies();
	}

	@PostMapping
	public Policy save(@RequestBody Policy policy) {
		return policyService.save(policy);
	}
}
