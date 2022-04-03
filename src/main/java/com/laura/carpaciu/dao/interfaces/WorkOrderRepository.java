package com.laura.carpaciu.dao.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.order.WorkOrder;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Integer> {

}
