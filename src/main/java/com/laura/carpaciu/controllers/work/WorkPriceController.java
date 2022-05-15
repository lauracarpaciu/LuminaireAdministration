package com.laura.carpaciu.controllers.work;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.carpaciu.entity.work.WorkPrice;
import com.laura.carpaciu.errors.luminaire.InvalidPriceException;
import com.laura.carpaciu.services.WorkPriceService;
import com.laura.carpaciu.utility.WorkCategory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/prices")
public class WorkPriceController {
	
	private final WorkPriceService workPriceService;

	@Autowired
	public WorkPriceController(WorkPriceService workPriceService) {
		super();
		this.workPriceService = workPriceService;
	}

	@GetMapping("/showPrices")
	public String showWorkPricePage(Model model) {

		WorkPrice workPrice = workPriceService.findAllPrices();

		model.addAttribute("price", new WorkPrice());
		model.addAttribute("workPrices", workPrice);
		model.addAttribute("category", WorkCategory.values());

		System.out.println(workPrice);

		return "/work/work-price";
	}

	@PostMapping("/setPrices")
	public String setWorkPrices(@Valid @ModelAttribute("price") WorkPrice workPrice, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			model.addAttribute("workPrices", new WorkPrice());
			model.addAttribute("category", WorkCategory.values());

			return "/work/work-price";

		}
		workPriceService.createAllWorkPrices(workPrice);

		return "redirect:/prices/showPrices";
	}

	@PostMapping("/updatePrice")
	public String updatePrice(HttpServletRequest request) {

		try {

			String categoryPrice = request.getParameter("category");
			double newPrice = Double.parseDouble(request.getParameter("updatePrice"));
			workPriceService.updatePrices(newPrice, categoryPrice);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new InvalidPriceException("wrong format number");

		}

		return "redirect:/prices/showPrices";
	}
}