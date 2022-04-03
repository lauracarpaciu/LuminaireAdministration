package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.order.PieceOrder;
import com.laura.carpaciu.entity.order.ServiceOrder;

public interface PieceOrderRepository extends CrudRepository<PieceOrder, Integer>{

	Optional<PieceOrder> findPartOrderByPartName(String partNumber, ServiceOrder serviceOrder);

	void createPieceOrder(PieceOrder partServiceOrder);

	void updatePartOrderCount(int id, int count);

	int deletePartFromServiceOrder(String partNumber);
	
//	void createPieceOrder(PieceOrder partServiceOrder);
//
//	Optional<PieceOrder> findPartOrderByPartName(String partNumber, ServiceOrder serviceOrder);
//
//	int deletePartFromServiceOrder(String partNumber);
//
//	int updatePartOrderCount(int id, int increment);

}
