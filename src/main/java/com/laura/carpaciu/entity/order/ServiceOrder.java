package com.laura.carpaciu.entity.order;

import javax.persistence.*;

import com.laura.carpaciu.entity.clients.Client;
import com.laura.carpaciu.entity.luminaire.Luminaire;
import com.laura.carpaciu.entity.user.User;
import com.laura.carpaciu.utility.OrderStatus;

import java.util.*;

@Entity
@Table(name = "serviceOrders")
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private LuminaireCases luminaireProblems;

	@ManyToOne
	private User user;

	@ManyToOne
	private Client client;

	@ManyToOne
	private Luminaire luminaire;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(mappedBy = "serviceOrder")
	private List<PieceOrder> parts = new ArrayList<>();

	@OneToMany(mappedBy = "serviceOrder")
	private List<WorkOrder> works = new ArrayList<>();

	@Transient
	private double partsTotalPrice;

	@Transient
	private double partsTotalPriceVAT;

	@Transient
	private double workTotalPrice;

	@Transient
	private double workTotalPriceVAT;

	@Transient
	public double totalPrice;

	@Transient
	public double totalPriceVAT;

	public ServiceOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceOrder(int id, LuminaireCases luminaireProblems, User user, Client client, Luminaire luminaire,
			OrderStatus orderStatus, List<PieceOrder> parts, List<WorkOrder> works, double partsTotalPrice,
			double partsTotalPriceVAT, double workTotalPrice, double workTotalPriceVAT, double totalPrice,
			double totalPriceVAT) {
		super();
		this.id = id;
		this.luminaireProblems = luminaireProblems;
		this.user = user;
		this.client = client;
		this.luminaire = luminaire;
		this.orderStatus = orderStatus;
		this.parts = parts;
		this.works = works;
		this.partsTotalPrice = partsTotalPrice;
		this.partsTotalPriceVAT = partsTotalPriceVAT;
		this.workTotalPrice = workTotalPrice;
		this.workTotalPriceVAT = workTotalPriceVAT;
		this.totalPrice = totalPrice;
		this.totalPriceVAT = totalPriceVAT;
	}



	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalPriceVAT() {
		return totalPriceVAT;
	}

	public void setTotalPriceVAT(double totalPriceVAT) {
		this.totalPriceVAT = totalPriceVAT;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LuminaireCases getLuminaireProblems() {
		return luminaireProblems;
	}

	public void setLuminaireProblems(LuminaireCases luminaireProblems) {
		this.luminaireProblems = luminaireProblems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Luminaire getLuminaire() {
		return luminaire;
	}

	public void setLuminaire(Luminaire luminaire) {
		this.luminaire = luminaire;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<PieceOrder> getParts() {
		return parts;
	}

	public void setParts(List<PieceOrder> parts) {
		this.parts = parts;
	}

	public List<WorkOrder> getWorks() {
		return works;
	}

	public void setWorks(List<WorkOrder> works) {
		this.works = works;
	}

	public double getPartsTotalPrice() {
		return partsTotalPrice;
	}

	public void setPartsTotalPrice(double partsTotalPrice) {
		this.partsTotalPrice = partsTotalPrice;
	}

	public double getPartsTotalPriceVAT() {
		return partsTotalPriceVAT;
	}

	public void setPartsTotalPriceVAT(double partsTotalPriceVAT) {
		this.partsTotalPriceVAT = partsTotalPriceVAT;
	}

	public double getWorkTotalPrice() {
		return workTotalPrice;
	}

	public void setWorkTotalPrice(double workTotalPrice) {
		this.workTotalPrice = workTotalPrice;
	}

	public double getWorkTotalPriceVAT() {
		return workTotalPriceVAT;
	}

	public void setWorkTotalPriceVAT(double workTotalPriceVAT) {
		this.workTotalPriceVAT = workTotalPriceVAT;
	}



	public static class Builder {

		private final ServiceOrder serviceOrder = new ServiceOrder();

		public Builder withId(int id) {
			serviceOrder.id = id;
			return this;
		}

		public Builder withTotalPrice(double totalPrice) {
			serviceOrder.totalPrice = totalPrice;
			return this;
		}

		public Builder withLuminaireProblems(LuminaireCases luminaireProblems) {
			serviceOrder.luminaireProblems = luminaireProblems;
			return this;
		}

		public Builder withUser(User user) {
			serviceOrder.user = user;
			return this;
		}

		public Builder withClient(Client client) {
			serviceOrder.client = client;
			return this;
		}

		public Builder withVehicle(Luminaire luminaire) {
			serviceOrder.luminaire = luminaire;
			return this;
		}

		public Builder withOrderStatus(OrderStatus open) {
			serviceOrder.orderStatus = open;
			return this;
		}

		public Builder withPart(List<PieceOrder> parts) {
			serviceOrder.parts = parts;
			return this;
		}

		public Builder withWorks(List<WorkOrder> works) {
			serviceOrder.works = works;
			return this;
		}

		public ServiceOrder build() {
			return serviceOrder;
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ServiceOrder that = (ServiceOrder) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ServiceOrder{" + "id=" + id + ", totalPrice=" + totalPrice + ", client=" + client + '}';
	}

}
