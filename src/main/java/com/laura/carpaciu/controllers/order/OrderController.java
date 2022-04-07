package com.laura.carpaciu.controllers.order;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laura.carpaciu.cache.MiniCache;
import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.entity.order.LuminaireCases;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.services.UserService;
import com.laura.carpaciu.utility.OrderStatus;

import lombok.AllArgsConstructor;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/serviceOrder")
public class OrderController {


    private final OrderService serviceOrderService;
    private final UserService userService;
    private final MiniCache miniCache;


    public OrderController(OrderService serviceOrderService, UserService userService, MiniCache miniCache) {
		super();
		this.serviceOrderService = serviceOrderService;
		this.userService = userService;
		this.miniCache = miniCache;
	}


	@GetMapping("/serviceOrder")
    public String showServiceOrderPage(Model model){

        Optional.ofNullable(miniCache.retrieveLuminaire())
                    .ifPresentOrElse(vehicle -> model.addAttribute("vehicle", vehicle),
                                          () -> model.addAttribute("vehicle", miniCache.getEmptyLuminaire()));

        Optional.ofNullable(miniCache.retrievePerson())
                    .ifPresentOrElse(person -> model.addAttribute("person", person),
                                         () -> model.addAttribute("person", miniCache.loadEmptyPerson()));

        Optional.ofNullable(miniCache.retrieveCompany())
                    .ifPresentOrElse(company -> model.addAttribute("company", company),
                                          () -> model.addAttribute("company", miniCache.loadEmptyCompany()));

        return "order/serviceOrder-page";
    }


    @GetMapping("/searchCar")
    public String searchCarByVin(HttpServletRequest request, Model model){

       String serialNumber = request.getParameter("serialNumber");
        Luminaire luminaire = miniCache.findLuminaireBySerialNumber(serialNumber);
        model.addAttribute("luminaire", luminaire);

        return "redirect:/serviceOrder/serviceOrder";
    }



    @GetMapping("/findPerson")
    public String searchPersonByCnp(HttpServletRequest request, Model model){

        String cnp = request.getParameter("cnp");
        Person  person = miniCache.findPersonByCnp(cnp);
        model.addAttribute("person", person);
        miniCache.resetCompanySearch();                                     // -> resetez company daca am dat search person

        return "redirect:/serviceOrder/serviceOrder";

    }

    @GetMapping("/findCompany")
    public String searchCompany(HttpServletRequest request, Model model){

        String cui = request.getParameter("cui");
        Company company = miniCache.findCompanyByCui(cui);
        model.addAttribute("company", company);
        miniCache.resetPersonSearch();                                      // -> resetez person daca am dat search company

        return "redirect:/serviceOrder/serviceOrder";
    }


    @PostMapping("/carProblems")    
    public String createServiceOrder(HttpServletRequest request){


        if(miniCache.retrieveLuminaire().getId() != 0 &&
                ((miniCache.retrievePerson().getId() != 0) || (miniCache.retrieveCompany().getId() != 0))){


            String luminaireProblems = request.getParameter("luminaireProblems");

            String username = SecurityContextHolder
                                                .getContext()
                                                .getAuthentication()
                                                .getPrincipal()
                                                .toString();


            User user = userService.findUseByUsername(username);
            LuminaireCases problems = new LuminaireCases();
                        problems.setProblems(luminaireProblems);


            ServiceOrder serviceOrder = new ServiceOrder.Builder()
                                                .withOrderStatus(OrderStatus.OPEN)
                                                .withLuminaireProblems(problems)
                                                .withVehicle(miniCache.retrieveLuminaire())
                                                .withUser(user)
                                                .build();



            if(miniCache.retrievePerson().getId() == 0){
                serviceOrder.setClient(miniCache.retrieveCompany());
            }

            if(miniCache.retrieveCompany().getId() == 0){
                serviceOrder.setClient(miniCache.retrievePerson());
            }


            serviceOrderService.createServiceOrder(serviceOrder);

			miniCache.resetCarSearch();
			miniCache.resetPersonSearch();
            miniCache.resetCompanySearch();
        }


        return "redirect:/serviceOrder/serviceOrder";
    }
}