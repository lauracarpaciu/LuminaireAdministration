package com.laura.carpaciu.dao.interfaces;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.invoice.Invoice;
import com.laura.carpaciu.entity.order.ServiceOrder;

@Repository
public interface InvoiceRepository {

	void saveInvoiceToDatabase(Invoice invoice);

	 Optional<Invoice> findInvoiceByServiceOrder(ServiceOrder serviceOrder);
}
