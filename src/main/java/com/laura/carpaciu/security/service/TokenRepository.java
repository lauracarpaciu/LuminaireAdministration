package com.laura.carpaciu.security.service;

public interface TokenRepository {

	int updateToken(int userId, String token);

}
