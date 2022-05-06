package com.laura.carpaciu.controllers.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.laura.carpaciu.entity.work.Work;
import com.laura.carpaciu.services.WorkService;
import com.laura.carpaciu.utility.WorkCategory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/works")
public class WorkController {
	@Autowired
	private final WorkService workService;

	private List<Work> works = new ArrayList<>();

	
	public WorkController(WorkService workService) {

		this.workService = workService;
	}

	@GetMapping("/work")
	public String getWorkPage(Model model) {

		model.addAttribute("work", new Work());
		model.addAttribute("workCategory", WorkCategory.values());
		model.addAttribute("workList", works);
		model.addAttribute("workUpdate", new Work());

		return "/work/work-page";
	}

	@PostMapping("/create-work")
	public String createWork(@Valid @ModelAttribute("work") Work work, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("workCategory", WorkCategory.values());
			return "/work/work-page";
		}

		workService.createWork(work);
		return "redirect:/works/work";
	}

	@GetMapping("/worksList")
	public String getWorks(HttpServletRequest request, Model model) {

		if (request.getParameter("workDescription") == null || request.getParameter("workDescription").equals("")) {

			works = workService.findAllWorks();
			model.addAttribute("workList", works);

		} else {

			String workDescription = request.getParameter("workDescription");
			works = workService.findWorkByName(workDescription);
			model.addAttribute("workList", works);
		}

		if (request.getParameter("workId") != null) {

			int workId = Integer.parseInt(request.getParameter("workId"));
			Work work = workService.findWorkById(workId);
			model.addAttribute("workUpdate", work);
		}

		model.addAttribute("work", new Work());
		model.addAttribute("workCategory", WorkCategory.values());

		return "/work/work-page";

	}

	@PostMapping("/update")
	public String updatework(@ModelAttribute("workUpdate") Work work) {

		workService.updateWorkTimeAndDescription(work.getTimedWork(), work.getWorkDescription(), work.getId());

		return "redirect:/works/worksList";
	}

	@GetMapping("/delete")
	public String deleteWork(@RequestParam("workId") Long id) {

		workService.deleteWork(id);

		return "redirect:/works/worksList";
	}

}