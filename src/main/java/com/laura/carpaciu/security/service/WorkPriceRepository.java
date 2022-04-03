package com.laura.carpaciu.security.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.work.WorkPrice;

public interface WorkPriceRepository extends CrudRepository<WorkPrice, Integer> {
	
	void createAllWorkPrices(WorkPrice workPrice);

	Optional<WorkPrice> findAllWorkPrices();

	int updateMechanicalWorkPrice(double mechanicalWorkPrice, int id);

	int updateHouseWorkPrice(double houseWorkPrice, int id);

	int updateElectricalWorkPrice(double electricalWorkPrice, int id);

	int updateNormalWorkPrice(double normalWorkPrice, int id);

}
