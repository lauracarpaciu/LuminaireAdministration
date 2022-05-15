package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.clients.Company;



//This will be AUTO IMPLEMENTED by Spring into a Bean called companyRepository
//CRUD refers Create, Read, Update, Delete
@Repository
public interface CompanyRepository {

	Optional<Company> findByCui(String cui);

	void create(Company company);


}
