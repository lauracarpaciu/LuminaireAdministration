package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.utility.OrderStatus;

public interface OrderRepository extends CrudRepository<ServiceOrder, Long> {

	void create(ServiceOrder serviceOrder);

	Set<ServiceOrder> findAllServiceOrders();

	ServiceOrder update(ServiceOrder serviceOrder);

	ServiceOrder findParts(int id);

	List<PieceOrder> getPartsFromServiceOrder(Long id);

	List<WorkOrder> findAllWorksInOrder(Long id);

	ServiceOrder findCompleteServiceOrderById(int id);

	List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

	Optional<ServiceOrder> findById(int id);

	int update(OrderStatus ready, Long orderId);

}
