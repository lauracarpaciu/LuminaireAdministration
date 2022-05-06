package com.laura.carpaciu.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.work.Work;

public interface WorkService {
	@Transactional
	void createWork(Work work);

	@Transactional(readOnly = true)
	List<Work> findAllWorks();

	@Transactional(readOnly = true)
	List<Work> findWorkByName(String workDescription);

	@Transactional(readOnly = true)
	Work findWorkById(int workId);

	@Transactional
	int updateWorkTimeAndDescription(double timedWork, String workDescription, Long id);

	@Transactional
	int deleteWork(Long id);

}
