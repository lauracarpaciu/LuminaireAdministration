package com.laura.carpaciu.entity.clients;

import javax.persistence.*;

import com.laura.carpaciu.entity.order.ServiceOrder;

import java.util.HashSet;
import java.util.Set;

@Entity //This tells Hibernate to make a table out of this class
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Embedded
	private Adress adress;

	@OneToMany(mappedBy = "client")
	private Set<ServiceOrder> serviceOrders = new HashSet<>();

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(int id, Adress adress, Set<ServiceOrder> serviceOrders) {
		super();
		this.id = id;
		this.adress = adress;
		this.serviceOrders = serviceOrders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Set<ServiceOrder> getServiceOrders() {
		return serviceOrders;
	}

	public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
		this.serviceOrders = serviceOrders;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", adress=" + adress + ", serviceOrders=" + serviceOrders + "]";
	}

}