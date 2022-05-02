package com.laura.carpaciu.entity.clients;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Objects;

@Embeddable
@Table(name = "adresses")
public class Adress {

	private String streetNumber;
	private String streetName;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Adress adress = (Adress) o;
		return Objects.equals(streetNumber, adress.streetNumber) && Objects.equals(streetName, adress.streetName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(streetNumber, streetName);
	}

	public Adress() {
		super();
		
	}

	public Adress(String streetNumber, String streetName) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

}
