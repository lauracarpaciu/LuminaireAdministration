package com.laura.carpaciu.dao.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.laura.carpaciu.entity.clients.Company;
import com.laura.carpaciu.entity.order.WorkOrder;
import com.laura.carpaciu.entity.user.User;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Integer> {
	
	void createUser(User user);

	Optional<User> findUserByUsername(String username);

	Optional<User> findUserByEmail(String email);

	int activateUserAccount(User user);

	Optional<User> findUserWithToken(String email);

}
