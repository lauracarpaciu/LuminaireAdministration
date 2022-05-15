package com.laura.carpaciu.dao.impl.work;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.WorkPriceRepository;
import com.laura.carpaciu.entity.work.WorkPrice;

import javax.persistence.EntityManager;
import java.util.Optional;


@Transactional(propagation = Propagation.MANDATORY)
@Service
public class WorkPrieceRepositoryImpl implements WorkPriceRepository{
	
	private final EntityManager entityManager;

	public WorkPrieceRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Optional<WorkPrice> findAllWorkPrices() {
		String jpql = "SELECT p FROM LaborPrice p";

        return entityManager.createQuery(jpql, WorkPrice.class)
                            .getResultStream()
                            .findFirst();
	}

	@Override
	public void create(WorkPrice workPrice) {
		 entityManager.persist(workPrice);

		
	}

	@Override
	public int updateMechanicalWorkPrice(double mechanicalLaborPrice, Long id) {
		String jpql = "UPDATE LaborPrice l SET l.mechanicalLaborPrice =: mechanicalLaborPrice WHERE l.id =: id ";

        return entityManager.createQuery(jpql)
                            .setParameter("mechanicalLaborPrice", mechanicalLaborPrice)
                            .setParameter("id", id)
                            .executeUpdate();
	}

	@Override
	public int updateHouseWorkPrice(double bodyWorkPrice, Long id) {
		String jpql = "UPDATE LaborPrice l SET l.bodyLaborPrice =: bodyLaborPrice WHERE l.id =: id";

        return entityManager.createQuery(jpql)
                            .setParameter("bodyLaborPrice",bodyWorkPrice)
                            .setParameter("id", id)
                            .executeUpdate();
		
	}

	@Override
	public int updateElectricalWorkPrice(double electricalWorkPrice, Long id) {
		  String jpql = "UPDATE LaborPrice l SET l.electricalLaborPrice =: electricalLaborPrice WHERE l.id =: id";

	        return entityManager.createQuery(jpql)
	                            .setParameter("electricalLaborPrice", electricalWorkPrice)
	                            .setParameter("id", id)
	                            .executeUpdate();
		
	}

	@Override
	public int updateNormalWorkPrice(double normalWorkPrice, Long id) {
		String jpql = "UPDATE LaborPrice l SET l.normalLaborPrice =: normalLaborPrice WHERE l.id = :id ";

        return entityManager.createQuery(jpql)
                            .setParameter("normalLaborPrice", normalWorkPrice)
                            .setParameter("id", id)
                            .executeUpdate();
		
	}


}
