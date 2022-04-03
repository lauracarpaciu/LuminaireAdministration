package com.laura.carpaciu.dao.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;


public interface TokenRepository extends CrudRepository<Token, Integer>{

}
