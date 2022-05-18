package com.laura.carpaciu.dao.impl.work;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.dao.interfaces.WorkPriceRepository;
import com.laura.carpaciu.entity.work.WorkPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Service
public class WorkPrieceRepositoryImpl implements WorkPriceRepository {

	@PersistenceContext
	private final EntityManager entityManager;

	public WorkPrieceRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void create(WorkPrice workPrice) {
		entityManager.persist(workPrice);

	}

	@Override
	public Optional<WorkPrice> findAllWorkPrices() {
		String jpql = "SELECT p FROM WorkPrice p";

		return entityManager.createQuery(jpql, WorkPrice.class).getResultStream().findFirst();
	}

	@Override
	public int updateMechanicalWorkPrice(double mechanicalWorkPrice, Long id) {
		String jpql = "UPDATE WorkPrice l SET l.mechanicalWorkPrice =: mechanicalWorkPrice WHERE l.id =: id ";

		return entityManager.createQuery(jpql).setParameter("mechanicalWorkPrice", mechanicalWorkPrice)
				.setParameter("id", id).executeUpdate();
	}

	@Override
	public int updateHouseWorkPrice(double bodyWorkPrice, Long id) {
		String jpql = "UPDATE WorkPrice l SET l.bodyWorkPrice =: bodyWorkPrice WHERE l.id =: id";

		return entityManager.createQuery(jpql).setParameter("bodyWorkPrice", bodyWorkPrice).setParameter("id", id)
				.executeUpdate();

	}

	@Override
	public int updateElectricalWorkPrice(double electricalWorkPrice, Long id) {
		String jpql = "UPDATE WorkPrice l SET l.electricalWorkPrice =: electricalWorkPrice WHERE l.id =: id";

		return entityManager.createQuery(jpql).setParameter("electricalWorkPrice", electricalWorkPrice)
				.setParameter("id", id).executeUpdate();

	}

	@Override
	public int updateNormalWorkPrice(double normalWorkPrice, Long id) {
		String jpql = "UPDATE WorkPrice l SET l.normalWorkPrice =: normalWorkPrice WHERE l.id = :id ";

		return entityManager.createQuery(jpql).setParameter("normalWorkPrice", normalWorkPrice).setParameter("id", id)
				.executeUpdate();

	}

}
