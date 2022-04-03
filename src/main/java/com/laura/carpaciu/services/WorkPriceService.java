package com.laura.carpaciu.services;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.work.WorkPrice;

public interface WorkPriceService {

	@Transactional
	void findWorkPrices();

	@Transactional
	WorkPrice findAllPrices();

	@Transactional
	void createAllWorkPrices(WorkPrice workPrice);

	@Transactional
	void updatePrices(double newPrice, String workCategory);
}
