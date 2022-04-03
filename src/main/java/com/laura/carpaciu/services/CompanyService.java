package com.laura.carpaciu.services;


import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.clients.Company;

public interface CompanyService {
	
	@Transactional
	void createCompany(Company company);

	@Transactional(readOnly = true)
	Company findCompanyByCui(String cui);
}
