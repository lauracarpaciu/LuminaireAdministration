package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;

public interface OrderRepository extends CrudRepository<ServiceOrder, Long> {

	void create(ServiceOrder serviceOrder);

	Set<ServiceOrder> findAllServiceOrders();

	ServiceOrder update(ServiceOrder serviceOrder);

	ServiceOrder findParts(int id);

	List<PieceOrder> getPartsFromServiceOrder(int id);

	List<WorkOrder> findAllWorksInOrder(int id);

	ServiceOrder findCompleteServiceOrderById(int id);

	List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

	Optional<ServiceOrder> findById(int id);

	int update(String close, Long orderId);

}
