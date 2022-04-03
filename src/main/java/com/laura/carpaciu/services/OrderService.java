package com.laura.carpaciu.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.utility.OrderStatus;

public interface OrderService {

	@Transactional
	void createServiceOrder(ServiceOrder serviceOrder);

	@Transactional
	Set<ServiceOrder> findAllServiceOrder();

	@Transactional
	List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

	@Transactional
	ServiceOrder findServiceOrderById(int id);

	@Transactional
	ServiceOrder updateServiceOrder(ServiceOrder serviceOrder, int decrement, String partNumber);

	@Transactional
	ServiceOrder findServiceOrderParts(int id);

	@Transactional
	List<PieceOrder> getPartsFormServiceOrder(int id);

	@Transactional
	List<WorkOrder> findAllLaborsInOrder(int id);

	@Transactional
	ServiceOrder findCompleteServiceOrderById(int id);

	@Transactional
	int closeOrder(ServiceOrder serviceOrder);

}
