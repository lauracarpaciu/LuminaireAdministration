package com.laura.carpaciu.dao.impl.work;

import java.util.Optional;

import com.laura.carpaciu.dao.interfaces.WorkOrderRepository;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.utility.OrderStatus;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;


@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class WorkOrderRepositoryImpl implements WorkOrderRepository {
	
	@PersistenceContext
    private final EntityManager entityManager;

	public WorkOrderRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Optional<WorkOrder> findById(int id) {
		String jpql = "SELECT l FROM LaborOrder l WHERE l.id =: id ";

        return entityManager.createQuery(jpql, WorkOrder.class)
                            .setParameter("id", id)
                            .getResultStream()
                            .findFirst();
    }


	@Override
	public void create(WorkOrder workOrder) {
		 entityManager.persist(workOrder);

		
	}

	@Override
	public int deleteWorkFromOrder(int id) {
		 String jpql = "DELETE FROM LaborOrder l WHERE l.id =: id ";

	        return entityManager.createQuery(jpql)
	                            .setParameter("id", id)
	                            .executeUpdate();
	}

	
}
