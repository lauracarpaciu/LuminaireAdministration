package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
@Repository
public interface PieceOrderRepository extends CrudRepository<PieceOrder, Long> {

	Optional<PieceOrder> findByPartNumb(String partNumber, ServiceOrder serviceOrder);

	void create(PieceOrder partServiceOrder);

	int updatePartOrderCount(int id, int count);

	int delete(String partNumber);

}
