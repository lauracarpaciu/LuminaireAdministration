package com.laura.carpaciu.controllers.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.laura.carpaciu.cache.MiniCache;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.services.PieceOrderService;
@RestController
public class PieceOrderController {

	private final PieceOrderService partOrderService;

	@Autowired
	public PieceOrderController(PieceOrderService partOrderService, OrderService serviceOrderService,
			MiniCache miniCache) {
		super();
		this.partOrderService = partOrderService;

	}

	@RequestMapping(value = "/pieceorders", method = RequestMethod.PUT)
	public ResponseEntity<?> addPartToServiceOrder(Piece part, ServiceOrder serviceOrder, int count) throws Exception {
		return Optional.ofNullable(partOrderService.addPartToServiceOrder(part, serviceOrder, count))
				.map(a -> new ResponseEntity<Employee>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

}
