package com.example.demo.xmlEntities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShoppingInfo")
public class ShoppingInfo {

	private Customers customers;
	private Orders orders;

	@XmlElement(name = "Customers")
	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@XmlElement(name = "Orders")
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

//	private List<Customers> customers;
//	private List<Orders> orders;
//
//	@XmlElement(name = "Customers")
//	public List<Customers> getCustomers() {
//		return customers;
//	}
//
//	public void setCustomers(List<Customers> customers) {
//		this.customers = customers;
//	}
//
//	@XmlElement(name = "Orders")
//	public List<Orders> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Orders> orders) {
//		this.orders = orders;
//	}
}
