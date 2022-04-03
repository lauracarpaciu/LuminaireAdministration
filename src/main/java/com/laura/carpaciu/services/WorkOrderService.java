package com.laura.carpaciu.services;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.order.ServiceOrder;

import com.laura.carpaciu.entity.work.Work;

public interface WorkOrderService {

	@Transactional
	void addWorkToServiceOrder(Work work, ServiceOrder serviceOrder);

	@Transactional
	void deleteWorkFromOrder(int id, ServiceOrder order);

}
