package com.laura.carpaciu.entity.work;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "workPrices")
public class WorkPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "required")
	@Min(value = 0, message = "invalid")
	private Double mechanicalWorkPrice;

	@NotNull(message = "required")
	@Min(value = 0, message = "invalid")
	private Double houseWorkPrice;

	@NotNull(message = "required")
	@Min(value = 0, message = "invalid")
	private Double electricalWorkPrice;

	@NotNull(message = "required")
	@Min(value = 0, message = "invalid")
	private Double normalWorkPrice;

	public WorkPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkPrice(int id, Double mechanicalWorkPrice, Double houseWorkPrice, Double electricalWorkPrice,
			Double normalWorkPrice) {
		super();
		this.id = id;
		this.mechanicalWorkPrice = mechanicalWorkPrice;
		this.houseWorkPrice = houseWorkPrice;
		this.electricalWorkPrice = electricalWorkPrice;
		this.normalWorkPrice = normalWorkPrice;
	}

	@Override
	public String toString() {
		return "WorkPrice [id=" + id + ", mechanicalWorkPrice=" + mechanicalWorkPrice + ", houseWorkPrice="
				+ houseWorkPrice + ", electricalWorkPrice=" + electricalWorkPrice + ", normalWorkPrice="
				+ normalWorkPrice + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getMechanicalWorkPrice() {
		return mechanicalWorkPrice;
	}

	public void setMechanicalWorkPrice(Double mechanicalWorkPrice) {
		this.mechanicalWorkPrice = mechanicalWorkPrice;
	}

	public Double getHouseWorkPrice() {
		return houseWorkPrice;
	}

	public void setHouseWorkPrice(Double houseWorkPrice) {
		this.houseWorkPrice = houseWorkPrice;
	}

	public Double getElectricalWorkPrice() {
		return electricalWorkPrice;
	}

	public void setElectricalWorkPrice(Double electricalWorkPrice) {
		this.electricalWorkPrice = electricalWorkPrice;
	}

	public Double getNormalWorkPrice() {
		return normalWorkPrice;
	}

	public void setNormalWorkPrice(Double normalWorkPrice) {
		this.normalWorkPrice = normalWorkPrice;
	}


}