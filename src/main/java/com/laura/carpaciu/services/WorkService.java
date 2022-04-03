package com.laura.carpaciu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.work.Work;

public interface WorkService  {
	@Transactional
	void createWork(Work work);

	@Transactional(readOnly = true)
	List<Work> findAllWorks();

	@Transactional(readOnly = true)
	List<Work> findWorkByName(String workDescription);

	@Transactional(readOnly = true)
	Work findWorkById(int id);

	@Transactional
	int updateWorkTimeAndDescription(double timedWork, String workDescription, int id);

	@Transactional
	int deleteWork(int id);

}
