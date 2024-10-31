package com.example.demo.xmlEntities;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Customer")
public class Customer {

    private String customerID;
	private String CompanyName;
	private String ContactName;
	private String ContactTitle;
	private String Phone;
	private List<Address> address;

    @XmlAttribute(name = "CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	@XmlElement(name = "ContactName")
	public String getContactName() {
		return ContactName;
	}

	public void setContactName(String contactName) {
		ContactName = contactName;
	}

	@XmlElement(name = "ContactTitle")
	public String getContactTitle() {
		return ContactTitle;
	}

	public void setContactTitle(String contactTitle) {
		ContactTitle = contactTitle;
	}

	@XmlElement(name = "Phone")
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	@XmlElement(name = "FullAddress")
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

}
