package com.laura.carpaciu.dao.interfaces;

import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;

public interface OrderRepository extends CrudRepository<Order, Integer>{

}
