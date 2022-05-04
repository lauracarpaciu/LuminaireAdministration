package com.laura.carpaciu.dao.impl.invoice;

import com.laura.carpaciu.dao.interfaces.InvoiceRepository;
import com.laura.carpaciu.entity.invoice.Invoice;
import com.laura.carpaciu.entity.order.ServiceOrder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

	private final EntityManager entityManager;

	public InvoiceRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	 @Override
	    public void saveInvoiceToDatabase(Invoice invoice){
	        entityManager.persist(invoice);
	    }


	    @Override
	    public Optional<Invoice> findInvoiceByServiceOrder(ServiceOrder serviceOrder){

	        String jpql = "SELECT i FROM Invoice i WHERE i.serviceOrder =: serviceOrder";


	        return entityManager.createQuery(jpql, Invoice.class)
	                            .setParameter("serviceOrder", serviceOrder)
	                            .getResultStream()
	                            .findFirst();
	    }
  

}
