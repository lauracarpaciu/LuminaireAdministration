package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.work.WorkPrice;

public interface WorkPriceRepository extends CrudRepository<WorkPrice, Integer> {

	Optional<WorkPrice> findAllWorkPrices();
//	
//	void createAllWorkPrices(WorkPrice workPrice);
//
//	Optional<WorkPrice> findAllWorkPrices();
//
//	int updateMechanicalWorkPrice(double mechanicalWorkPrice, int id);
//
//	int updateHouseWorkPrice(double houseWorkPrice, int id);
//
//	int updateElectricalWorkPrice(double electricalWorkPrice, int id);
//
//	int updateNormalWorkPrice(double normalWorkPrice, int id);

	void createAllWorkPrices(WorkPrice workPrice);

	void updateMechanicalWorkPrice(double newPrice, Integer id);

	void updateHouseWorkPrice(double newPrice, Integer id);

	void updateElectricalWorkPrice(double newPrice, Integer id);

	void updateNormalWorkPrice(double newPrice, Integer id);

	Optional<WorkPrice> findAllWorkPrices();

}
