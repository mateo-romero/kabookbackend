package com.kabook.kabook.service;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.Policy;

import java.util.List;
import java.util.Set;

public interface IPolicyService {
	List<Policy> findAllPolicies();
	Policy save(Policy policy);
	boolean savePolicyFromProduct(ProductDTO productDTO);
}
