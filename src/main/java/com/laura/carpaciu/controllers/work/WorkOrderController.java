package com.laura.carpaciu.controllers.work;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.work.Work;
import com.laura.carpaciu.services.WorkOrderService;
@RestController
public class WorkOrderController {

	private final WorkOrderService workOrderService;

	@Autowired
	public WorkOrderController(WorkOrderService workOrderService) {
		super();
		this.workOrderService = workOrderService;
	}

	@RequestMapping(value = "/works", method = RequestMethod.POST)
	public ResponseEntity<?> addWorkToServiceOrder(@RequestBody Work work, @RequestBody ServiceOrder serviceOrder) throws Exception {
		return Optional.ofNullable(workOrderService.addWorkToServiceOrder(work, serviceOrder))
				.map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/works/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWorkFromOrder(@PathVariable int id, ServiceOrder order) throws Exception {
        return Optional.ofNullable(workOrderService.deleteWorkFromOrder(id,  order))
                .map(a -> new ResponseEntity<List<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Not found"));
    }

}
