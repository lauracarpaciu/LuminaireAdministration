package com.laura.carpaciu.services;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.order.ServiceOrder;

public interface InvoiceService {

	@Transactional
	void saveInvoiceToDatabase(ServiceOrder serviceOrder);

	@Transactional
	void getInvoiceFromDataBase(ServiceOrder serviceOrder, HttpServletResponse response);

}
