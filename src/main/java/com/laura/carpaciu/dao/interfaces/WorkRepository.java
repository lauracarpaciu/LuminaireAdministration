package com.laura.carpaciu.dao.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.work.Work;

public interface WorkRepository extends CrudRepository<Work, Integer> {

}
