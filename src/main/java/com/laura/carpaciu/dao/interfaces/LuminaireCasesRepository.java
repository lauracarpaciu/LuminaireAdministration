package com.laura.carpaciu.dao.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.order.LuminaireCases;

public interface LuminaireCasesRepository extends CrudRepository<LuminaireCases, Integer>{
	
	void createLuminaireCases(LuminaireCases luminaireProblems);

	List<LuminaireCases> luminaireProblemsList();

}
