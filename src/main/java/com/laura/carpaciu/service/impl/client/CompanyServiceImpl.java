package com.laura.carpaciu.service.impl.client;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.CompanyRepository;
import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.errors.client.CompanyAlreadyExistsException;
import com.laura.carpaciu.services.CompanyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Lazy
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	@Override
	@Transactional
	public void createCompany(Company company) {

		Optional<Company> optCompany = companyRepository.findCompanyByCui(company.getCui());

		if (optCompany.empty() != null) {
			companyRepository.createCompany(company);
			return;
		}

		throw new CompanyAlreadyExistsException("Company Already exists");
	}

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Company findCompanyByCui(String cui) {

		return companyRepository.findCompanyByCui(cui).orElseThrow();

	}
}