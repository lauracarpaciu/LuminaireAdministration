package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.utility.OrderStatus;
@Repository
public interface WorkOrderRepository  {
	Optional<WorkOrder> findById(int id);

	void create(WorkOrder workOrder);

	int deleteWorkFromOrder(int id);

	}
