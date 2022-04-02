package com.laura.carpaciu.entity.clients;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Company extends Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "required")
	@Size(max = 30, message = " must have a maximum of {max} characters ")
	@Pattern(regexp = "[0-9]+", message = "only digits")
	private String cui;

	@NotNull(message = "required")
	@Size(max = 100, message = " must have a maximum of {max} characters ")
	private String name;

	

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(int id, Adress adress, Set<ServiceOrder> serviceOrders) {
		super(id, adress, serviceOrders);
		// TODO Auto-generated constructor stub
	}

		
	public Company(int id, String cui, String name) {
		super();
		this.id = id;
		this.cui = cui;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCui() {
		return cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Company company = (Company) o;
		return Objects.equals(cui, company.cui);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cui);
	}

	@Override
	public String toString() {
		return "Company{" + "id=" + id + ", cui='" + cui + '\'' + ", name='" + name + '\'' + '}';
	}
}
