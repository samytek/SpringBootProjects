package com.example.demo.xmlEntities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Order")
public class Order {

	private String CustomerID;
	private String EmployeeID;
	private String OrderDate;
	private String RequiredDate;
	private List<ShipInfo> ShipInfo;
	
	@XmlElement(name = "CustomerID")
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	
	@XmlElement(name = "EmployeeID")
	public String getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}
	
	@XmlElement(name = "OrderDate")
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	
	@XmlElement(name = "RequiredDate")
	public String getRequiredDate() {
		return RequiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		RequiredDate = requiredDate;
	}
	
	@XmlElement(name = "ShipInfo")
	public List<ShipInfo> getShipInfo() {
		return ShipInfo;
	}
	public void setShipInfo(List<ShipInfo> shipInfo) {
		ShipInfo = shipInfo;
	}
	
	
	
}
