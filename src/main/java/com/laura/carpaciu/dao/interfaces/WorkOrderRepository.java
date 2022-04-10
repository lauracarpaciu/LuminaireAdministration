package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.order.WorkOrder;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {
	Optional<WorkOrder> findById(int id);

	void create(WorkOrder workOrder);

	int deleteWorkFromOrder(int id);

	void update(String ready, Long id);

}
