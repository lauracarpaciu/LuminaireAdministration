package com.laura.carpaciu.dao.impl.user;

import com.laura.carpaciu.dao.interfaces.TokenRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TokenRepositoryImpl implements TokenRepository {

	private final EntityManager entityManager;

	public TokenRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public int updateToken(Long userId, String token) {

		String jpql = "UPDATE ActivationToken a SET a.token =: token, a.createdAt =: createdAt, a.expiredAt =: expiredAt WHERE a.user.id =: userId";

		return entityManager.createQuery(jpql).setParameter("token", token)
				.setParameter("createdAt", LocalDateTime.now())
				.setParameter("expiredAt", LocalDateTime.now().plusMinutes(2)).setParameter("userId", userId)
				.executeUpdate();
	}

}
