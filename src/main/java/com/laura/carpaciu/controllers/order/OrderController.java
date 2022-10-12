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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
@RestController
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
	public ResponseEntity<?> createServiceOrder(@RequestBody ServiceOrder serviceOrder) throws Exception {
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

	@RequestMapping(value = "/orders/{partNumber}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateServiceOrder(@RequestBody ServiceOrder serviceOrder, int decrement,@PathVariable String partNumber)
			throws Exception {
		return Optional.ofNullable(serviceOrderService.updateServiceOrder(serviceOrder, decrement, partNumber))
				.map(a -> new ResponseEntity<ServiceOrder>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findServiceOrderParts(@PathVariable Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.findServiceOrderParts(id))
				.map(a -> new ResponseEntity<ServiceOrder>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders/{}id", method = RequestMethod.GET)
	public ResponseEntity<?> getPartsFormServiceOrder(@PathVariable Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.getPartsFormServiceOrder(id))
				.map(a -> new ResponseEntity<List<PieceOrder>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCompleteServiceOrderById(@PathVariable Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.findCompleteServiceOrderById(id))
				.map(a -> new ResponseEntity<List<PieceOrder>>(HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<?> closeOrder(ServiceOrder serviceOrder) throws Exception {
		return Optional.ofNullable(serviceOrderService.closeOrder(serviceOrder))
				.map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAllWorksInOrder(@PathVariable Long id) throws Exception {
		return Optional.ofNullable(serviceOrderService.findAllWorksInOrder(id))
				.map(a -> new ResponseEntity<List<WorkOrder>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Not found"));
	}

}
