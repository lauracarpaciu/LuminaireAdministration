package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.work.WorkPrice;
@Repository
public interface WorkPriceRepository  {

	Optional<WorkPrice> findAllWorkPrices();

	void create(WorkPrice workPrice);

	int updateMechanicalWorkPrice(double mechanicalWorkPrice, Long id);

	int updateHouseWorkPrice(double newPrice, Long id);

	int updateElectricalWorkPrice(double newPrice, Long id);

	int updateNormalWorkPrice(double newPrice, Long id);

}
