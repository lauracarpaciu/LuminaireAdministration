package com.laura.carpaciu.dao.interfaces;

import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository {

	int updateToken(Long id, String token);

}
