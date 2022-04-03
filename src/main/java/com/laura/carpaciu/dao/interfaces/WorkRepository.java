package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.work.Work;

public interface WorkRepository extends CrudRepository<Work, Integer> {
//	void createWork(Work work);
//
//	List<Work> findWorkByName(String workDescription);
//
//	int updateWorkTimeAndDescription(double timedWork, String workDescription, int id);
//
//	List<Work> findAllWorks();
//
//	Optional<Work> findWorkById(int id);
//
//	int deleteWork(int id);


}
