package com.laura.carpaciu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;

public interface CompanyService {
	
	void createCompany(Company company);

	Optional<Company> findCompanyByCui(String cui);

	List<Company> findCompanyByName(String companyName);

}
