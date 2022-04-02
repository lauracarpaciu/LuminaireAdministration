package com.laura.carpaciu.entity.invoice;

import javax.persistence.*;

import com.laura.carpaciu.entity.order.ServiceOrder;

import java.util.Objects;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String invoiceNumber;

	@Lob
	private byte[] invoice;

	@OneToOne(fetch = FetchType.LAZY)
	private ServiceOrder serviceOrder;

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(int id, String invoiceNumber, byte[] invoice, ServiceOrder serviceOrder) {
		super();
		this.id = id;
		this.invoiceNumber = invoiceNumber;
		this.invoice = invoice;
		this.serviceOrder = serviceOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public byte[] getInvoice() {
		return invoice;
	}

	public void setInvoice(byte[] invoice) {
		this.invoice = invoice;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Invoice invoice = (Invoice) o;
		return id == invoice.id && Objects.equals(invoiceNumber, invoice.invoiceNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, invoiceNumber);
	}

	@Override
	public String toString() {
		return "Invoice{" + "id=" + id + ", number='" + invoiceNumber + '\'' + '}';
	}}

