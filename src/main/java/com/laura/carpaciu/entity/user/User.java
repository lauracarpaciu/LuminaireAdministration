package com.laura.carpaciu.entity.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.laura.carpaciu.entity.order.ServiceOrder;
import java.util.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "required")
	@Size(min = 2, max = 20)
	@Column(name = "first_Name")
	private String firstName;

	@NotNull(message = "required")
	@Size(min = 2, message = "must have atleast 2 characters")
	@Column(name = "last_Name")
	private String lastName;
	@NotNull(message = "required")
	@Size(min = 2, message = "must have atlest 2 characterrs")
	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@Size(min = 3, message = "password must have at least 3 characters")
	private String password;

	private boolean isEnabled = false;
	private boolean isNonLoked = false;

	@ElementCollection(fetch = FetchType.LAZY)
	@Size(min = 1, message = "select at lest one")
	private Set<String> authorities = new HashSet<>();

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "user")
	private ActivationToken activationToken;

	@OneToMany(mappedBy = "user")
	private Set<ServiceOrder> serviceOrders = new HashSet<>();

	public User() {
	}

	public User(Long id, @NotNull(message = "required") @Size(min = 2, max = 20) String firstName,
			@NotNull(message = "required") @Size(min = 2, message = "must have atleast 2 characters") String lastName,
			@NotNull(message = "required") @Size(min = 2, message = "must have atlest 2 characterrs") String username,
			String email, @Size(min = 3, message = "password must have at least 3 characters") String password,
			boolean isEnabled, boolean isNonLoked,
			@Size(min = 1, message = "select at lest one") Set<String> authorities, ActivationToken activationToken,
			Set<ServiceOrder> serviceOrders) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isNonLoked = isNonLoked;
		this.authorities = authorities;
		this.activationToken = activationToken;
		this.serviceOrders = serviceOrders;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(username, user.username) || Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, email);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + "]";
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isNonLoked() {
		return isNonLoked;
	}

	public void setNonLoked(boolean isNonLoked) {
		this.isNonLoked = isNonLoked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActivationToken getActivationToken() {
		return activationToken;
	}

	public void setActivationToken(ActivationToken activationToken) {
		this.activationToken = activationToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}