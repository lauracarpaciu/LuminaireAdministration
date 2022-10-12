package com.laura.carpaciu.controllers.order;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.services.CompanyService;
import com.laura.carpaciu.services.InvoiceService;
@RestController
public class InvoiceController {

	private final InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.POST)
	public ResponseEntity<?> saveInvoiceToDatabase(@RequestBody ServiceOrder serviceOrder) throws Exception {
		return Optional.ofNullable(invoiceService.saveInvoiceToDatabase(serviceOrder))
				.map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public ResponseEntity<?> getInvoiceFromDataBase(ServiceOrder serviceOrder, HttpServletResponse response)
			throws Exception {
		return Optional.ofNullable(invoiceService.getInvoiceFromDataBase(serviceOrder, response))
				.map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElseThrow(() -> new Exception("Not found"));
	}

}
