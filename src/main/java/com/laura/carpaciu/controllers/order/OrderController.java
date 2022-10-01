package com.laura.carpaciu.controllers.order;

import com.laura.carpaciu.cache.MiniCache;
import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.services.UserService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class OrderController {

	private final OrderService serviceOrderService;

	private final UserService userService;

	private final MiniCache miniCache;

	@Autowired
	public OrderController(OrderService serviceOrderService, UserService userService, MiniCache miniCache) {
		super();
		this.serviceOrderService = serviceOrderService;
		this.userService = userService;
		this.miniCache = miniCache;
	}

	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public ResponseEntity<?> createServiceOrder(ServiceOrder serviceOrder) throws Exception {
		return Optional.ofNullable(serviceOrderService.createServiceOrder(serviceOrder))
				.map(a -> new ResponseEntity<Employee>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> findAllServiceOrder() throws Exception {
		return Optional.ofNullable(serviceOrderService.findAllServiceOrder())
				.map(a -> new ResponseEntity<Set<ServiceOrder>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> allServiceOrderIdAndStatus() throws Exception {
		return Optional.ofNullable(serviceOrderService.allServiceOrderIdAndStatus())
				.map(a -> new ResponseEntity<List<ServiceOrderIdAndStatusDto>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.PUT)
	public ResponseEntity<?> updateServiceOrder(ServiceOrder serviceOrder, int decrement, String partNumber)
			throws Exception {
		return Optional.ofNullable(serviceOrderService.updateServiceOrder(serviceOrder, decrement, partNumber))
				.map(a -> new ResponseEntity<ServiceOrder>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> findServiceOrderParts(Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.findServiceOrderParts(id))
				.map(a -> new ResponseEntity<ServiceOrder>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> getPartsFormServiceOrder(Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.getPartsFormServiceOrder(id))
				.map(a -> new ResponseEntity<List<PieceOrder>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> findCompleteServiceOrderById(Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.findCompleteServiceOrderById(id))
				.map(a -> new ResponseEntity<List<PieceOrder>>(HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> closeOrder(ServiceOrder serviceOrder) throws Exception {
		return Optional.ofNullable(serviceOrderService.closeOrder(serviceOrder))
				.map(a -> new ResponseEntity<int>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> findAllWorksInOrder(Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.findAllWorksInOrder(id))
				.map(a -> new ResponseEntity<List<WorkOrder>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

}
