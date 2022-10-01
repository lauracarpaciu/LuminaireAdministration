package com.laura.carpaciu.services;

import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.luminaire.Piece;

import com.laura.carpaciu.entity.order.ServiceOrder;

public interface PieceOrderService {
	
	

	@Transactional
	void addPartToServiceOrder(Piece part, ServiceOrder serviceOrder, int count);

	@Transactional
	int deletePartFromServiceOrder(String partNumber, int count, ServiceOrder order);

}
