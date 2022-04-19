package com.laura.carpaciu.controllers.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laura.carpaciu.cache.MiniCache;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.errors.order.BadIntegerNumberException;
import com.laura.carpaciu.errors.order.PartNotFoundException;
import com.laura.carpaciu.errors.order.SelectOrderException;
import com.laura.carpaciu.errors.order.SelectPartException;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.services.PieceOrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/orderPiece")
public class PieceOrderController {

	private final PieceOrderService partOrderService;
	private final OrderService serviceOrderService;
	private final MiniCache miniCache;

	public PieceOrderController(PieceOrderService partOrderService, OrderService serviceOrderService,
			MiniCache miniCache) {
		super();
		this.partOrderService = partOrderService;
		this.serviceOrderService = serviceOrderService;
		this.miniCache = miniCache;
	}

	@GetMapping("/addPiece-page")
	public String addPiecesToServiceOrder(Model model) {

		if (miniCache.getCompleteServiceOrder() == null) {
			throw new SelectOrderException("No order selected!");
		}

		Long id = miniCache.getCompleteServiceOrder().getId();

		List<PieceOrder> partServiceOrders = serviceOrderService.getPartsFormServiceOrder(id);

		model.addAttribute("serviceOrderParts", partServiceOrders);

		return "order/pieceOrder-page";
	}

	@GetMapping("/findPart")
	public String findPart(HttpServletRequest request, Model model) {

		String partNumber = request.getParameter("partNumber");
		Piece piece;

		try {
			piece = miniCache.findPartByPartNumber(partNumber);
		} catch (PartNotFoundException e) {
			e.printStackTrace();

			throw new PartNotFoundException();
		}

		model.addAttribute("piece", piece);
		return "redirect:/orderPart/addPiece-page";
	}

	@PostMapping("/addPartToOrder")
	public String addPartToOrder(HttpServletRequest request) {

		if (miniCache.retrievePart() == null) {
			throw new SelectPartException("No part was selected");
		}

		try {

			int count = Integer.parseInt(request.getParameter("count"));
			ServiceOrder serviceOrder = miniCache.getCompleteServiceOrder();
			partOrderService.addPartToServiceOrder(miniCache.retrievePart(), serviceOrder, count);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new BadIntegerNumberException("The value is not an integer");

		}

		String partNumber = miniCache.retrievePart().getPartNumber();
		miniCache.findPartByPartNumber(partNumber);
		return "redirect:/orderPart/addPiece-page";
	}

	@GetMapping("/deletePart")
	public String deletePartFromServiceOrder(@RequestParam("partNumber") String partNumber,
			@RequestParam("count") int count) {

		partOrderService.deletePartFromServiceOrder(partNumber, count, miniCache.getCompleteServiceOrder());
		miniCache.findPartByPartNumber(partNumber);
		return "redirect:/orderPart/addPiece-page";
	}

}