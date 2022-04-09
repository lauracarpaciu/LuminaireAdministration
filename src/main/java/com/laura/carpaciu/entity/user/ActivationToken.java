package com.laura.carpaciu.entity.user;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "activationTokens")
public class ActivationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String token;
	private LocalDateTime createdAt;
	private LocalDateTime activatedAt;
	private LocalDateTime expiredAt;
	@OneToOne
	private User user;

	public ActivationToken(int id, String token, LocalDateTime createdAt, LocalDateTime activatedAt,
			LocalDateTime expiredAt, User user) {
		super();
		this.id = id;
		this.token = token;
		this.createdAt = createdAt;
		this.activatedAt = activatedAt;
		this.expiredAt = expiredAt;
		this.user = user;
	}

	public ActivationToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(LocalDateTime activatedAt) {
		this.activatedAt = activatedAt;
	}

	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activatedAt, createdAt, expiredAt, id, token, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivationToken other = (ActivationToken) obj;
		return Objects.equals(activatedAt, other.activatedAt) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(expiredAt, other.expiredAt) && id == other.id && Objects.equals(token, other.token)
				&& Objects.equals(user, other.user);
	}

	
}
