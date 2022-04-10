package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.work.WorkPrice;

public interface WorkPriceRepository extends CrudRepository<WorkPrice, Long> {

	Optional<WorkPrice> findAllWorkPrices();

	void create(WorkPrice workPrice);

	void updateMechanicalWorkPrice(double newPrice, Long id);

	void updateHouseWorkPrice(double newPrice, Long id);

	void updateElectricalWorkPrice(double newPrice, Long id);

	void updateNormalWorkPrice(double newPrice, Long id);

}
