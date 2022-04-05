package com.laura.carpaciu.cache.impl;
import com.laura.carpaciu.cache.*;
import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.clients.Person;
import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.entity.work.Work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.laura.carpaciu.services.CompanyService;
import com.laura.carpaciu.services.LuminaireService;
import com.laura.carpaciu.services.OrderService;
import com.laura.carpaciu.services.PersonService;
import com.laura.carpaciu.services.PieceService;
import com.laura.carpaciu.services.WorkService;

@Component
public class MiniCacheImpl implements MiniCache {  

    private final OrderService serviceOrderService;
    private final WorkService workService;
    private final PieceService pieceService;
    private final LuminaireService luminaireService;
    private final PersonService personService;
    private final CompanyService companyService;

    private final Map<String, ServiceOrder> order = new HashMap<>();
    private final Map<String, List<Work>> works = new HashMap<>();
    private final Map<String, List<WorkOrder>> orderWorks = new HashMap<>();
    private final Map<String, Piece> parts = new HashMap<>();
    private final Map<String, Luminaire> luminaire = new HashMap<>();
    private final Map<String, Person> person = new HashMap<>();
    private final Map<String, Company> company = new HashMap<>();


    @Autowired
    public MiniCacheImpl(OrderService serviceOrderService, WorkService workService, PieceService pieceService,
			LuminaireService luminaireService, PersonService personService, CompanyService companyService) {
		
		this.serviceOrderService = serviceOrderService;
		this.workService = workService;
		this.pieceService = pieceService;
		this.luminaireService = luminaireService;
		this.personService = personService;
		this.companyService = companyService;
	}

	

	@Override
	public ServiceOrder loadCompleteServiceOrderById(int id) {
		ServiceOrder serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);

        order.put(username(), serviceOrder);

        return order.get(username());
	}



	@Override
	public ServiceOrder getCompleteServiceOrder() {
		 return order.get(username());
	}


	@Override
	public void loadWorksOrder() {
		orderWorks.put(username(), serviceOrderService.
                findAllWorksInOrder(getCompleteServiceOrder().getId()));
		
	}


	@Override
	public List<WorkOrder> retrieveWorkFromOrder() {
		return orderWorks.get(username());
	}


	@Override
	public void retrieveWorks(String workDescription) {
		works.put(username(), workService.findWorkByName(workDescription));
		
	}


	@Override
	public List<Work> retrieveWorks() {
		return works.get(username());
	}


	@Override
	public Piece findPartByPartNumber(String partNumber) {
		parts.put(username(),pieceService.findPartByPartNumber(partNumber));

        return parts.get(username());
	}


	@Override
	public Luminaire findLuminaireBySerialNumber(String serialNumber) {
		luminaire.put(username(), luminaireService.findLuminaireBySerialNumber(serialNumber));
        return luminaire.get(username());
	}


	@Override
	public Luminaire retrieveLuminaire() {
		return luminaire.get(username());
	}


	@Override
	public Luminaire getEmptyLuminaire() {
		luminaire.put(username(), new Luminaire());
        return luminaire.get(username());
	}


	@Override
	public Person findPersonByCnp(String cnp) {
		person.put(username(), personService.findPersonByCnp(cnp));
        return person.get(username());
	}


	@Override
	public Person loadEmptyPerson() {
		person.put(username(), new Person());
        return person.get(username());
	}


	@Override
	public Person retrievePerson() {
		return person.get(username());
	}


	@Override
	public Company findCompanyByCui(String cui) {
		company.put(username(),companyService.findCompanyByCui(cui));
        return company.get(username());
	}


	@Override
	public Company retrieveCompany() {
		return company.get(username());
	}


	@Override
	public Company loadEmptyCompany() {
		company.put(username(), new Company());
        return company.get(username());
	}


	@Override
	public Piece retrievePart() {
		return parts.get(username());
	}


	 @Override
	    public void resetCompanySearch(){
	        company.put(username(), new Company());
	    }


	    @Override
	    public void resetPersonSearch(){
	        person.put(username(), new Person());
	    }


	    @Override
	    public void resetCarSearch(){
	        luminaire.put(username(), new Luminaire());
	    }




	    public String username(){
	        return SecurityContextHolder.getContext().getAuthentication().getName();
	    }


	    @Override
	    public Map<String, ServiceOrder> getOrder() {
	        return order;
	    }

	    @Override
	    public Map<String, List<Work>> getWorks() {
	        return works;
	    }

	    @Override
	    public Map<String, List<WorkOrder>> getOrderWorks() {
	        return orderWorks;
	    }

	    @Override
	    public Map<String, Piece> getParts() {
	        return parts;
	    }

	    @Override
	    public Map<String, Luminaire> getLuminaire() {
	        return luminaire;
	    }

	    @Override
	    public Map<String, Person> getPerson() {
	        return person;
	    }

	    @Override
	    public Map<String, Company> getCompany() {
	        return company;
	    }
}