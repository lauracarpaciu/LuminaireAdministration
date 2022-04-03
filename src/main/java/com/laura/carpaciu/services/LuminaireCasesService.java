package com.laura.carpaciu.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.order.LuminaireCases;

public interface LuminaireCasesService {

	void createLuminaireCases(LuminaireCases luminaireProblems);

	List<LuminaireCases> luminaireProblemsList();

}
