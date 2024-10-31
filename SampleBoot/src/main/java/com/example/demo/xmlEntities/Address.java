package com.example.demo.xmlEntities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FullAddress")
public class Address {

	private String Address;
	private String City;
	private String Region;
	private String PostalCode;
	private String Country;

	@XmlElement(name = "Address")
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	@XmlElement(name = "City")
	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	@XmlElement(name = "Region")
	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	@XmlElement(name = "PostalCode")
	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	@XmlElement(name = "Country")
	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	@Override
	public String toString() {
		return "Address [Address=" + Address + ", City=" + City + ", Region=" + Region + ", PostalCode=" + PostalCode
				+ ", Country=" + Country + "]";
	}
}
