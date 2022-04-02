package com.laura.carpaciu.entity.order;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PieceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "part_Number")
	private String partNumber;

	@Column(name = "part_Name")
	private String partName;

	@Column(name = "count")
	private int count;

	@Column(name = "price")
	private double price;

	@ManyToOne
	private ServiceOrder serviceOrder;

	public PieceOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PieceOrder(int id, String partNumber, String partName, int count, double price, ServiceOrder serviceOrder) {
		super();
		this.id = id;
		this.partNumber = partNumber;
		this.partName = partName;
		this.count = count;
		this.price = price;
		this.serviceOrder = serviceOrder;
	}

	@Override
	public int hashCode() {
		return Objects.hash(partName, partNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PieceOrder other = (PieceOrder) obj;
		return Objects.equals(partName, other.partName) && Objects.equals(partNumber, other.partNumber);
	}

	@Override
	public String toString() {
		return "PieceOrder [id=" + id + ", partNumber=" + partNumber + ", partName=" + partName + ", count=" + count
				+ ", price=" + price + "]";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}





}
