package com.laura.carpaciu.controllers.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.carpaciu.cache.MiniCache;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.errors.order.SelectOrderException;
import com.laura.carpaciu.services.WorkOrderService;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/workOrder")
public class WorkOrderController {

	private final MiniCache miniCache;
	private final WorkOrderService workOrderService;

	public WorkOrderController(MiniCache miniCache, WorkOrderService workOrderService) {
		super();
		this.miniCache = miniCache;
		this.workOrderService = workOrderService;
	}

	@GetMapping("/workOrderPage")
	public String getLaborOrderPage(Model model) {

		if (miniCache.getCompleteServiceOrder() == null) {
			throw new SelectOrderException("No order selected!");
		}

		miniCache.loadWorksOrder();

		model.addAttribute("works", miniCache.retrieveWorks());
		model.addAttribute("orderWorks", miniCache.retrieveWorkFromOrder());
		model.addAttribute("order", miniCache.getCompleteServiceOrder());

		return "order/workOrder-page";
	}

	@GetMapping("/searchWork")
	public String searchWork(HttpServletRequest request, Model model) {

		String workDescription = request.getParameter("workDescription");
		miniCache.retrieveWorks(workDescription);

		return "redirect:/workOrder/workOrderPage";
	}

	@GetMapping("/addWorkToOrder")
	public String addLaborToOrder(HttpServletRequest request, Model model) {

		ServiceOrder serviceOrder = miniCache.getCompleteServiceOrder();

		int id = Integer.parseInt(request.getParameter("workId"));

		miniCache.retrieveWorks().stream().filter(u -> u.getId() == id).findFirst()
				.ifPresent(l -> workOrderService.addWorkToServiceOrder(l, serviceOrder));

		return "redirect:/workOrder/workOrderPage";
	}

	@PostMapping("/deleteWork")
	public String deleteLaborOrder(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("laborId"));

		workOrderService.deleteWorkFromOrder(id, miniCache.getCompleteServiceOrder());

		return "redirect:/workOrder/workOrderPage";
	}
}