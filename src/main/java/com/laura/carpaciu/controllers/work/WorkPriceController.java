package com.laura.carpaciu.controllers.work;

import com.laura.carpaciu.entity.work.WorkPrice;
import com.laura.carpaciu.services.WorkPriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

public class WorkPriceController {
	
	private final WorkPriceService workpriceservice;
	
	@Autowired
	public WorkPriceController(WorkPriceService workpriceservice) {
		super();
		this.workpriceservice = workpriceservice;
	}


	@RequestMapping(value = "/employes", method = RequestMethod.POST)
    public ResponseEntity<?> findWorkPrices() throws Exception {
        return Optional.ofNullable(workpriceservice.findWorkPrices())
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Not found"));
    }


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<?> findAllPrices() throws Exception {
        return Optional.ofNullable(workpriceservice.findAllPrices())
                .map(a -> new ResponseEntity<WorkPrice>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Not found"));
    }
    
    
    @RequestMapping(value = "/employes", method = RequestMethod.POST)
    public ResponseEntity<?> createAllWorkPrices(WorkPrice workPrice) throws Exception {
        return Optional.ofNullable(workpriceservice.createAllWorkPrices( workPrice))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Not found"));
    }


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<?> updatePrices(double newPrice, String workCategory) throws Exception {
        return Optional.ofNullable(workpriceservice.updatePrices( newPrice, workCategory))
                .map(a -> new ResponseEntit<>>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Not found"));
    }

}
