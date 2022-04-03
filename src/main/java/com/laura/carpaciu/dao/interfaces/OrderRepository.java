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
import com.laura.carpaciu.utility.OrderStatus;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	void createServiceOrder(ServiceOrder serviceOrder);

	Set<ServiceOrder> findAllServiceOrders();

	List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

	Optional<ServiceOrder> findServiceOrderById(int id);

	ServiceOrder updateServiceOrder(ServiceOrder serviceOrder);

	ServiceOrder findServiceOrderParts(int id);

	List<PieceOrder> getPartsFormServiceOrder(int id);

	List<WorkOrder> findAllWorksInOrder(int id);

	ServiceOrder findCompleteServiceOrderById(int id);

	int updateOrderStatus(OrderStatus orderStatus, int id);

}
