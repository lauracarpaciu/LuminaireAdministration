package com.laura.carpaciu.service.impl.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.convertor.WorkConvertor;
import com.laura.carpaciu.dao.interfaces.OrderRepository;
import com.laura.carpaciu.dao.interfaces.WorkOrderRepository;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.entity.work.Work;
import com.laura.carpaciu.entity.work.WorkPrice;
import com.laura.carpaciu.errors.order.WorkNotFoundException;
import com.laura.carpaciu.errors.work.WorkOrderException;
import com.laura.carpaciu.service.impl.work.WorkPriceServiceImpl;
import com.laura.carpaciu.services.WorkOrderService;
import com.laura.carpaciu.util.TwoDigitsDouble;
import com.laura.carpaciu.utility.OrderStatus;

import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

	private final WorkOrderRepository workOrderRepository;
	private final WorkPriceServiceImpl workPriceService;

	public WorkOrderServiceImpl(WorkOrderRepository workOrderRepository, WorkPriceServiceImpl workPriceService,
			OrderRepository orderRepository) {
		super();
		this.workOrderRepository = workOrderRepository;
		this.workPriceService = workPriceService;

	}

	@Transactional
	public void addWorkToServiceOrder(Work work, ServiceOrder order) {

		if (order.getOrderStatus().toString().equals("CLOSE")) {
			throw new WorkOrderException("Order is CLOSED, can't add any more workss to it!");
		}

		double workPrice = this.workPrice(work);
		workPrice = TwoDigitsDouble.formatPrice(workPrice);
		WorkOrder workServiceOrder = WorkConvertor.convert(work, workPrice, order);

		workOrderRepository.create(workServiceOrder);
		workOrderRepository.update(OrderStatus.READY, order.getId());

	}

	private double workPrice(Work work) {

		String workCategory = work.getCategory().toString();
		Optional<WorkPrice> workPrice = workPriceService.getOptWorkPrice();

		if (workPrice.isPresent()) {

			switch (workCategory) {

			case "MECHANICAL": {
				return work.getTimedWork() * workPrice.get().getMechanicalWorkPrice();

			}

			case "HOUSE": {
				return work.getTimedWork() * workPrice.get().getHouseWorkPrice();

			}

			case "ELECTRIC": {
				return work.getTimedWork() * workPrice.get().getElectricalWorkPrice();
			}

			case "NORMAL": {
				return work.getTimedWork() * workPrice.get().getNormalWorkPrice();
			}

			}
		}
		return 0.0;
	}

	@Override
	@Transactional
	public void deleteWorkFromOrder(int id, ServiceOrder order) {

		if (order.getOrderStatus().toString().equals("CLOSE")) {
			throw new WorkOrderException("Order is CLOSED, can't add any more labors to it!");
		}

		Optional<WorkOrder> workOrder = workOrderRepository.findById(id);

		if (workOrder.isPresent()) {
			workOrderRepository.deleteWorkFromOrder(id);
			return;
		}

		throw new WorkNotFoundException("Work was already deleted from the order!");
	}
}
