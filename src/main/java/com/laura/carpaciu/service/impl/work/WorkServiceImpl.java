package com.laura.carpaciu.service.impl.work;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.WorkRepository;
import com.laura.carpaciu.entity.work.Work;
import com.laura.carpaciu.services.WorkService;

public class WorkServiceImpl implements WorkService {
	@Autowired
	private final WorkRepository workRepository;

	public WorkServiceImpl(WorkRepository workRepository) {
		super();
		this.workRepository = workRepository;
	}

	@Override
	@Transactional
	public void createWork(Work work)

	{
		workRepository.create(work);
	}

	@Override
	@Transactional
	public List<Work> findAllWorks() {

		return workRepository.findAllWorks();
	}

	@Override
	@Transactional
	public List<Work> findWorkByName(String workDescription) {

		return workRepository.findByName(workDescription);
	}

	@Override
	@Transactional(readOnly = true)
	public Work findWorkById(int id) {

		return workRepository.findById(id).orElseThrow(() -> new RuntimeException("Labor id not found"));

	}

	@Override
	@Transactional
	public int deleteWork(int id) {

		java.util.Optional<Work> work = workRepository.findById(id);

		if (work.isPresent()) {
			return workRepository.deleteWork(id);
		}

		throw new RuntimeException("Work not found");
	}

	@Override
	@Transactional
	public int updateWorkTimeAndDescription(double timedWork, String workDescription, Long id) {
		// TODO Auto-generated method stub
		return workRepository.updateWorkTimeAndDescription(timedWork, workDescription, id);
	}

}
