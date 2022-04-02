package com.laura.carpaciu.entity.luminaire;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;
@Entity
@Table(name = "pices")
public class Piece {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "required")
	@Size(min = 1, message = "required")
	@Column(name = "part_Number")
	private String partNumber;

	@NotNull(message = "required")
	@Size(min = 1, message = "required")
	@Column(name = "part_Name")
	private String partName;

	@Min(value = 1, message = "must be equal or greater than {value}")
	@Max(value = 100000, message = "must be less or equal to {value}")
	@NotNull(message = "required")
	@Column(name = "count")
	private Integer count;

	@Min(value = 0, message = "must be equal or grater than {value}")
	@NotNull(message = "required")
	@Column(name = "price")
	private Double price;

	public Piece() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Piece(int id, String partNumber, String partName, Integer count, Double price) {
		super();
		this.id = id;
		this.partNumber = partNumber;
		this.partName = partName;
		this.count = count;
		this.price = price;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(partNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		return Objects.equals(partNumber, other.partNumber);
	}

	@Override
	public String toString() {
		return "Piece [id=" + id + ", partNumber=" + partNumber + ", partName=" + partName + ", count=" + count
				+ ", price=" + price + "]";
	}



}