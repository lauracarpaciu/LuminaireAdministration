package com.laura.carpaciu.dao.interfaces;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;

public interface OrderRepository extends CrudRepository<Order, Long> {

	void createServiceOrder(ServiceOrder serviceOrder);

	Set<ServiceOrder> findAllServiceOrders();

	Object findServiceOrderById(int id);

	ServiceOrder updateServiceOrder(ServiceOrder serviceOrder);

	ServiceOrder findServiceOrderParts(int id);

	List<PieceOrder> getPartsFromServiceOrder(int id);

	List<WorkOrder> findAllWorksInOrder(int id);

	int updateOrderStatus(String close, Integer orderId);

	ServiceOrder findCompleteServiceOrderById(int id);

	List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

}
