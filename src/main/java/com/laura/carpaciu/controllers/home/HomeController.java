package com.laura.carpaciu.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laura.carpaciu.cache.MiniCache;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.errors.order.SelectOrderException;
import com.laura.carpaciu.services.InvoiceService;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.utility.OrderStatus;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
@RequestMapping("/app")
public class HomeController {

	private final OrderService orderService;
	private final InvoiceService invoiceService;
	private final MiniCache miniCache;

	public HomeController(OrderService orderService, InvoiceService invoiceService, MiniCache miniCache) {
		super();
		this.orderService = orderService;
		this.invoiceService = invoiceService;
		this.miniCache = miniCache;
	}

	@GetMapping("/main")
	public String showHomePage(Model model) {

		if (miniCache.getCompleteServiceOrder() == null) {
			allModelAtributes(new ServiceOrder(), model);
		} else {

			allModelAtributes(miniCache.getCompleteServiceOrder(), model);
		}

		model.addAttribute("serviceOrderIdAndStatus", orderService.allServiceOrderIdAndStatus());

		return "home/home-page";
	}

	@GetMapping("/order-stats") // method 1
	public String findOrderStats(@RequestParam("orderId") int id, Model model) {

		ServiceOrder serviceOrder = miniCache.loadCompleteServiceOrderById(id);
		allModelAtributes(serviceOrder, model);

		return "redirect:/app/main";
	}

	@PostMapping("/closeOrder")
	public String closeOrder() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		if (miniCache.getCompleteServiceOrder() == null) {
			throw new SelectOrderException("No order selected");
		}
		orderService.closeOrder(miniCache.getCompleteServiceOrder());
		miniCache.getCompleteServiceOrder().setOrderStatus(OrderStatus.CLOSE);
		return "redirect:/app/main";
	}

	@GetMapping("/invoice")
	public void getInvoice(HttpServletResponse response, Model model) {

		if (miniCache.getCompleteServiceOrder() == null) {
			throw new SelectOrderException("No order selected");
		}

		invoiceService.getInvoiceFromDataBase(miniCache.getCompleteServiceOrder(), response);

		model.addAttribute("serviceOrderIdAndStatus", orderService.allServiceOrderIdAndStatus());
	}

	private void allModelAtributes(ServiceOrder serviceOrder, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("laborsOrder", serviceOrder.getWorks());
		model.addAttribute("partsOrder", serviceOrder.getParts());
		model.addAttribute("serviceOrder", serviceOrder);

		model.addAttribute("partsTotalPrice", serviceOrder.getPartsTotalPrice());
		model.addAttribute("partsTotalPriceWithVAT", serviceOrder.getPartsTotalPriceVAT());

		model.addAttribute("laborTotalPrice", serviceOrder.getWorkTotalPrice());
		model.addAttribute("laborTotalPriceWithVAT", serviceOrder.getWorkTotalPriceVAT());

		model.addAttribute("totalPrice", serviceOrder.getTotalPrice());
		model.addAttribute("totalPriceWithVAT", serviceOrder.getTotalPriceVAT());

		model.addAttribute("username", username);
	}

}
