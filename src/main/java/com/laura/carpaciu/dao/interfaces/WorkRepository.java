package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.work.Work;
@Repository
public interface WorkRepository {

	void create(Work work);

	List<Work> findAllWorks();

	List<Work> findByName(String workDescription);

	Optional<Work> findById(int id);

	int updateWorkTimeAndDescription(double timedWork, String workDescription, Long id);

	int deleteWork(int id);

}
