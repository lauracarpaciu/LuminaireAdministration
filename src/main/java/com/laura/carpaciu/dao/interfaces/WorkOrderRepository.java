package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.utility.OrderStatus;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {
	Optional<WorkOrder> findById(int id);

	void create(WorkOrder workOrder);

	int deleteWorkFromOrder(int id);

	void update(OrderStatus ready, Long id);

}
