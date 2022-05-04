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

public interface OrderRepository  {

	void createServiceOrder(ServiceOrder serviceOrder);


    Set<ServiceOrder> findAllServiceOrders();

    List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

    Optional<ServiceOrder> findServiceOrderById(Long id);

    ServiceOrder updateServiceOrder(ServiceOrder serviceOrder);


    ServiceOrder findServiceOrderParts(Long id);

    List<PieceOrder> getPartsFormServiceOrder(Long id);

    List<WorkOrder> findAllWorksInOrder(Long id);


    ServiceOrder findCompleteServiceOrderById(Long id);


    int updateOrderStatus(OrderStatus orderStatus, Long id);

}
