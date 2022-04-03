package com.laura.carpaciu.dao.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.order.PieceOrder;

public interface PieceOrderRepository extends CrudRepository<PieceOrder, Integer>{

}
