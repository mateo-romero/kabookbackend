package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.Image;
import com.kabook.kabook.model.Policy;
import com.kabook.kabook.repository.IPolicyRepository;
import com.kabook.kabook.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PolicyServiceImpl implements IPolicyService {
	@Autowired
	private IPolicyRepository policyRepository;

	@Override
	public List<Policy> findAllPolicies() {
		return policyRepository.findAll();
	}

	@Override
	public Policy save(Policy policy) {
		return policyRepository.save(policy);
	}

	@Override
	public boolean savePolicyFromProduct(ProductDTO productDTO) {
		Set<Policy> policySet = productDTO.getPolicies();
		if (policySet != null && !policySet.isEmpty()) {
			for (Policy policy : policySet) {
				policy.setId(productDTO.getId());
				Long idNewPolicy = save(policy).getId();
				if (idNewPolicy == null) {
					return false;
				}
			}
		}
		return true;
	}
}
