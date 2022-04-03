package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;



//This will be AUTO IMPLEMENTED by Spring into a Bean called companyRepository
//CRUD refers Create, Read, Update, Delete

public interface CompanyRepository {
	
	void createCompany(Company company);

	Optional<Company> findCompanyByCui(String cui);

	List<Company> findCompanyByName(String companyName);

}
