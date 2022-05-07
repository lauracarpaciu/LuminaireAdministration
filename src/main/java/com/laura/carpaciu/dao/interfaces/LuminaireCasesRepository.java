package com.laura.carpaciu.dao.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.order.LuminaireCases;
@Repository
public interface LuminaireCasesRepository extends CrudRepository<LuminaireCases, Long> {

//	void createLuminaireCases(LuminaireCases luminaireProblems);
//
//	List<LuminaireCases> luminaireProblemsList();

}
