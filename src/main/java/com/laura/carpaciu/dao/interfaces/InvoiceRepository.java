package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.invoice.Invoice;
import com.laura.carpaciu.entity.order.ServiceOrder;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer>{
	void saveInvoiceToDatabase(Invoice invoice);

	Optional<Invoice> findInvoiceByServiceOrder(ServiceOrder serviceOrder);

}
