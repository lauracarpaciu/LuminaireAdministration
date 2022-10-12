package com.laura.carpaciu.controllers.order;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class CompanyController {

	@Autowired
	private final CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@RequestMapping(value = "/companys", method = RequestMethod.POST)
	public ResponseEntity<?> createCompany(@RequestBody Company company) throws Exception {
		return Optional.ofNullable(companyService.createCompany(company))
				.map(a -> new ResponseEntity<Employee>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/companys/{cui}", method = RequestMethod.GET)
	public ResponseEntity<?> findCompanyByCui(@PathVariable String cui) throws Exception {
		return Optional.ofNullable(companyService.findCompanyByCui(cui))
				.map(a -> new ResponseEntity<Company>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

}
