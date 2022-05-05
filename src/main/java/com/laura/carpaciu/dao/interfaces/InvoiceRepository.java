package com.laura.carpaciu.dao.interfaces;


import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.invoice.Invoice;
import com.laura.carpaciu.entity.order.ServiceOrder;


public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

	void saveInvoiceToDatabase(Invoice invoice);

	Invoice findByServiceOrder(ServiceOrder serviceOrder);

}
