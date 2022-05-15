package com.laura.carpaciu.dao.impl.order;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.laura.carpaciu.dao.interfaces.OrderRepository;
import com.laura.carpaciu.dto.ServiceOrderIdAndStatusDto;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.utility.OrderStatus;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class OrderRepositoryImpl implements OrderRepository {
	
	@PersistenceContext
	private final EntityManager entityManager;

	public OrderRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void createServiceOrder(ServiceOrder serviceOrder) {
		entityManager.persist(serviceOrder);
	}

	@Override
	public Set<ServiceOrder> findAllServiceOrders() {

		String jpql = "SELECT s FROM ServiceOrder s";

		return entityManager.createQuery(jpql, ServiceOrder.class).getResultStream().collect(Collectors.toSet());
	}

	@Override
	public List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus() {

		String jpql = "SELECT  NEW  com.gini.iordache.dto.ServiceOrderIdAndStatusDto(s.id, s.orderStatus) FROM ServiceOrder s";

		return entityManager.createQuery(jpql, ServiceOrderIdAndStatusDto.class).getResultList();
	}

	@Override
	public Optional<ServiceOrder> findServiceOrderById(Long id) {

		String jpql = "SELECT s FROM ServiceOrder s WHERE s.id =: id";

		return entityManager.createQuery(jpql, ServiceOrder.class).setParameter("id", id).getResultStream().findFirst();

	}

	@Override
	public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder) {
		return entityManager.merge(serviceOrder);
	}

	@Override
	public ServiceOrder findServiceOrderParts(Long id) {

		String jpql = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.parts WHERE s.id =: id";

		return entityManager.createQuery(jpql, ServiceOrder.class).setParameter("id", id).getSingleResult();

	}

	@Override
	public List<PieceOrder> getPartsFormServiceOrder(Long id) {
		String jpql = "SELECT parts FROM ServiceOrder s JOIN s.parts parts WHERE s.id =: id";

		return entityManager.createQuery(jpql, PieceOrder.class).setParameter("id", id).getResultList();

	}

	@Override
	public List<WorkOrder> findAllWorksInOrder(Long id) {

		String jpql = "SELECT labors FROM ServiceOrder s JOIN s.labors labors WHERE s.id =: id";

		return entityManager.createQuery(jpql, WorkOrder.class).setParameter("id", id).getResultList();

	}

	@Override
	public ServiceOrder findCompleteServiceOrderById(Long id) {
		ServiceOrder serviceOrder = null;

		String jpql1 = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.parts WHERE s.id = :id";
		String jpql2 = "SELECT s FROM ServiceOrder s LEFT JOIN FETCh s.labors l WHERE s IN :serviceOrder";

		serviceOrder = entityManager.createQuery(jpql1, ServiceOrder.class).setParameter("id", id).getSingleResult();

		serviceOrder = entityManager.createQuery(jpql2, ServiceOrder.class).setParameter("serviceOrder", serviceOrder)
				.getSingleResult();

		return serviceOrder;
	}

	@Override
	public int updateOrderStatus(OrderStatus orderStatus, Long id) {

		String jpql = "UPDATE ServiceOrder s SET s.orderStatus =: orderStatus WHERE s.id =: id";

		return entityManager.createQuery(jpql).setParameter("orderStatus", orderStatus).setParameter("id", id)
				.executeUpdate();

	}

}
