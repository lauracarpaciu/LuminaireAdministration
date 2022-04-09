package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.work.WorkPrice;

public interface WorkPriceRepository extends CrudRepository<WorkPrice, Long> {

	Optional<WorkPrice> findAllWorkPrices();

	void create(WorkPrice workPrice);

	void updateMechanicalWorkPrice(double newPrice, Integer id);

	void updateHouseWorkPrice(double newPrice, Integer id);

	void updateElectricalWorkPrice(double newPrice, Integer id);

	void updateNormalWorkPrice(double newPrice, Integer id);

}
