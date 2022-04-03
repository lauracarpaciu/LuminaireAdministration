package com.laura.carpaciu.services;

import javax.servlet.http.HttpServletResponse;

import com.laura.carpaciu.entity.invoice.Invoice;
import com.laura.carpaciu.entity.order.ServiceOrder;

public interface InvoiceService {

	void saveInvoiceToDatabase(Invoice invoice);

	void getInvoiceFromDataBase(ServiceOrder serviceOrder, HttpServletResponse response);

}
