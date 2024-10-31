package com.example.demo.xmlEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER_INFO")
public class CustomerOrderInfo {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
	@SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_CUSTOMER_ORDER_INFO", allocationSize = 1)
	private int Id;
	
	@Column(name = "CUSTOMER_ID")
    private String customerID;
	
	@Column(name = "COMPANY_NAME")
	private String CompanyName;
	
	@Column(name = "CONTACT_NAME")
	private String ContactName;
	
	@Column(name = "CONTACT_TITLE")
	private String ContactTitle;
	
	@Column(name = "Phone")
	private String Phone;
	
	@Column(name = "Address")
	private String Address;
	
	@Column(name = "City")
	private String City;
	
	@Column(name = "Region")
	private String Region;
	
	@Column(name = "POSTAL_CODE")
	private String PostalCode;
	
	@Column(name = "Country")
	private String Country;
	
	@Column(name = "EMPLOYEE_ID")
	private String EmployeeID;
	
	@Column(name = "ORDER_DATE")
	private String OrderDate;
	
	@Column(name = "REQUIRED_DATE")
	private String RequiredDate;
	
	@Column(name = "SHIPPED_DATE")
	private String ShippedDate;
	
	@Column(name = "SHIP_VIA")
	private String ShipVia;
	
	@Column(name = "Freight")
	private String Freight;
	
	@Column(name = "SHIP_NAME")
	private String ShipName;
	
	@Column(name = "SHIP_ADDRESS")
	private String ShipAddress;
	
	@Column(name = "SHIP_CITY")
	private String ShipCity;
	
	@Column(name = "SHIP_REGION")
	private String ShipRegion;
	
	@Column(name = "SHIP_POSTAL_CODE")
	private String ShipPostalCode;
	
	@Column(name = "SHIP_COUNTRY")
	private String ShipCountry;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getContactName() {
		return ContactName;
	}

	public void setContactName(String contactName) {
		ContactName = contactName;
	}

	public String getContactTitle() {
		return ContactTitle;
	}

	public void setContactTitle(String contactTitle) {
		ContactTitle = contactTitle;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public String getRequiredDate() {
		return RequiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		RequiredDate = requiredDate;
	}

	public String getShippedDate() {
		return ShippedDate;
	}

	public void setShippedDate(String shippedDate) {
		ShippedDate = shippedDate;
	}

	public String getShipVia() {
		return ShipVia;
	}

	public void setShipVia(String shipVia) {
		ShipVia = shipVia;
	}

	public String getFreight() {
		return Freight;
	}

	public void setFreight(String freight) {
		Freight = freight;
	}

	public String getShipName() {
		return ShipName;
	}

	public void setShipName(String shipName) {
		ShipName = shipName;
	}

	public String getShipAddress() {
		return ShipAddress;
	}

	public void setShipAddress(String shipAddress) {
		ShipAddress = shipAddress;
	}

	public String getShipCity() {
		return ShipCity;
	}

	public void setShipCity(String shipCity) {
		ShipCity = shipCity;
	}

	public String getShipRegion() {
		return ShipRegion;
	}

	public void setShipRegion(String shipRegion) {
		ShipRegion = shipRegion;
	}

	public String getShipPostalCode() {
		return ShipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		ShipPostalCode = shipPostalCode;
	}

	public String getShipCountry() {
		return ShipCountry;
	}

	public void setShipCountry(String shipCountry) {
		ShipCountry = shipCountry;
	}

	@Override
	public String toString() {
		return "CustomerOrderInfo [Id=" + Id + ", customerID=" + customerID + ", CompanyName=" + CompanyName
				+ ", ContactName=" + ContactName + ", ContactTitle=" + ContactTitle + ", Phone=" + Phone + ", Address="
				+ Address + ", City=" + City + ", Region=" + Region + ", PostalCode=" + PostalCode + ", Country="
				+ Country + ", EmployeeID=" + EmployeeID + ", OrderDate=" + OrderDate + ", RequiredDate=" + RequiredDate
				+ ", ShippedDate=" + ShippedDate + ", ShipVia=" + ShipVia + ", Freight=" + Freight + ", ShipName="
				+ ShipName + ", ShipAddress=" + ShipAddress + ", ShipCity=" + ShipCity + ", ShipRegion=" + ShipRegion
				+ ", ShipPostalCode=" + ShipPostalCode + ", ShipCountry=" + ShipCountry + "]";
	}
}
