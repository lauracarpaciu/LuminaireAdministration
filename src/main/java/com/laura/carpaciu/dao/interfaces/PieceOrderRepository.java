package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;
@Repository
public interface PieceOrderRepository  {

	Optional<PieceOrder> findByPartNumb(String partNumber, ServiceOrder serviceOrder);

	void create(PieceOrder partServiceOrder);

	int updatePieceOrderCount(int id, int increment);

	int delete(String partNumber);

}
