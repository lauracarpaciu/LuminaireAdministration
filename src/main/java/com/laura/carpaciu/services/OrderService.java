package com.laura.carpaciu.services;

import java.util.List;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.errors.order.OrderIsClosedException;

public interface OrderService {

	@Transactional
	void createServiceOrder(ServiceOrder serviceOrder);

	@Transactional
	Set<ServiceOrder> findAllServiceOrder();

	@Transactional
	List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

	@Transactional
	ServiceOrder updateServiceOrder(ServiceOrder serviceOrder, int decrement, String partNumber);

	@Transactional
	ServiceOrder findServiceOrderParts(Long id);

	@Transactional
	List<PieceOrder> getPartsFormServiceOrder(Long id);

	@Transactional
	ServiceOrder findCompleteServiceOrderById(Long id);

	@Transactional
	int closeOrder(ServiceOrder serviceOrder) throws OrderIsClosedException;
	
	@Transactional
	List<WorkOrder> findAllWorksInOrder(Long id);
}
