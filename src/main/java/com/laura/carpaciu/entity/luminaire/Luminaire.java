package com.laura.carpaciu.entity.luminaire;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.laura.carpaciu.entity.order.ServiceOrder;

@Entity
@Table(name = "luminaires")
public class Luminaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "required")
	@Size(max = 30, message = "luminaire name is to long")
	@Column(name = "luminaire_manufacturer", nullable = false)
	private String luminaireManufacturer;

	@NotNull(message = "required")
	@Size(max = 30, message = "model name is to long")
	@Column(name = "luminaire_model", nullable = false)
	private String luminaireModel;

	@Size(max = 17, message = "VIN lenght can't be more than {max} characters")
	@NotNull(message = "required")
	@Pattern(regexp = "^[0-9]*[a-zA-Z]+[a-zA-Z0-9]*$", message = "invalid VIN")
	@Column(name = "serial_number_or_vin", unique = true)
	private String serialNumber;

	@OneToMany(mappedBy = "luminaire")
	private Set<ServiceOrder> serviceOrders = new HashSet<>();

	public Luminaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Luminaire(Long id,
			@NotNull(message = "required") @Size(max = 30, message = "luminaire name is to long") String luminaireManufacturer,
			@NotNull(message = "required") @Size(max = 30, message = "model name is to long") String luminaireModel,
			@Size(max = 17, message = "VIN lenght can't be more than {max} characters") @NotNull(message = "required") @Pattern(regexp = "^[0-9]*[a-zA-Z]+[a-zA-Z0-9]*$", message = "invalid VIN") String serialNumber,
			Set<ServiceOrder> serviceOrders) {
		super();
		this.id = id;
		this.luminaireManufacturer = luminaireManufacturer;
		this.luminaireModel = luminaireModel;
		this.serialNumber = serialNumber;
		this.serviceOrders = serviceOrders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Luminaire other = (Luminaire) obj;
		return Objects.equals(serialNumber, other.serialNumber);
	}

	@Override
	public String toString() {
		return "Luminaire [id=" + id + ", luminaireManufacturer=" + luminaireManufacturer + ", luminaireModel="
				+ luminaireModel + ", serialNumber=" + serialNumber + "]";
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
