package com.laura.carpaciu.dto;

import com.laura.carpaciu.utility.OrderStatus;

public class ServiceOrderIdAndStatusDto {

	private int id;
	private OrderStatus orderStatus;

	public ServiceOrderIdAndStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceOrderIdAndStatusDto(int id, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrderIdAndStatusDto other = (ServiceOrderIdAndStatusDto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceOrderIdAndStatusDto [id=" + id + ", orderStatus=" + orderStatus + "]";
	}

}
