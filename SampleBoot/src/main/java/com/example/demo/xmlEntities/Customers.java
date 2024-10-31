package com.example.demo.xmlEntities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Customers")
public class Customers {

	private List<Customer> customer;

    @XmlElement(name = "Customer")
	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
}
