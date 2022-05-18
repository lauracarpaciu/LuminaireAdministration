package com.laura.carpaciu.dao.impl.order;

import java.util.Optional;

import com.laura.carpaciu.dao.interfaces.PieceOrderRepository;
import com.laura.carpaciu.dao.interfaces.PieceRepository;
import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class PieceOrderRepositoryImpl implements PieceOrderRepository {

	@PersistenceContext
	private final EntityManager entityManager;

	public PieceOrderRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Optional<PieceOrder> findByPartNumb(String partNumber, ServiceOrder serviceOrder) {
		String jpql = "SELECT p FROM PartOrder p WHERE p.partNumber =: partNumber AND p.serviceOrder =: serviceOrder";

		return entityManager.createQuery(jpql, PieceOrder.class).setParameter("partNumber", partNumber)
				.setParameter("serviceOrder", serviceOrder).getResultStream().findFirst();
	}

	@Override
	public void create(PieceOrder partServiceOrder) {
		entityManager.persist(partServiceOrder);

	}

	@Override
	public int updatePieceOrderCount(int id, int increment) {
		String jpql = "UPDATE PartOrder p SET p.count = p.count + :increment WHERE p.id =: id";

		return entityManager.createQuery(jpql).setParameter("increment", increment).setParameter("id", id)
				.executeUpdate();

	}

	@Override
	public int delete(String partNumber) {
		String jpql = "DELETE FROM PartOrder p WHERE p.partNumber =: partNumber";

		return entityManager.createQuery(jpql).setParameter("partNumber", partNumber).executeUpdate();
	}

}
