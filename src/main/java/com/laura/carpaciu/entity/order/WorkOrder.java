package com.laura.carpaciu.entity.order;

import javax.persistence.*;

import com.laura.carpaciu.utility.WorkCategory;

import java.util.Objects;

@Entity
public class WorkOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String workDescription;

	private double timedWork;

	private double workPrice;

	@ManyToOne
	private ServiceOrder serviceOrder;

	@Enumerated(EnumType.STRING)
	private WorkCategory category;

	public WorkOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkOrder(int id, String workDescription, double timedWork, double workPrice, ServiceOrder serviceOrder,
			WorkCategory category) {
		super();
		this.id = id;
		this.workDescription = workDescription;
		this.timedWork = timedWork;
		this.workPrice = workPrice;
		this.serviceOrder = serviceOrder;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public double getTimedWork() {
		return timedWork;
	}

	public void setTimedWork(double timedWork) {
		this.timedWork = timedWork;
	}

	public double getWorkPrice() {
		return workPrice;
	}

	public void setWorkPrice(double workPrice) {
		this.workPrice = workPrice;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public WorkCategory getCategory() {
		return category;
	}

	public void setCategory(WorkCategory category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, timedWork, workDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkOrder other = (WorkOrder) obj;
		return category == other.category
				&& Double.doubleToLongBits(timedWork) == Double.doubleToLongBits(other.timedWork)
				&& Objects.equals(workDescription, other.workDescription);
	}

	@Override
	public String toString() {
		return "WorkOrder [id=" + id + ", workDescription=" + workDescription + ", timedWork=" + timedWork
				+ ", workPrice=" + workPrice + ", category=" + category + "]";
	}

}