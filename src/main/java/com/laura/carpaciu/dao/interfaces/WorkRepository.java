package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.laura.carpaciu.entity.work.Work;

public interface WorkRepository extends CrudRepository<Work, Long> {

	void create(Work work);

	List<Work> findAllWorks();

	List<Work> findByName(String workDescription);

	Optional<Work> findById(int id);

	int updateWorkTimeAndDescription(double timedWork, String workDescription, Long id);

	int deleteWork(Long id);

}
