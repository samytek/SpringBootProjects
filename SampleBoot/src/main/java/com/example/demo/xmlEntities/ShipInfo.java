package com.example.demo.xmlEntities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShipInfo")
public class ShipInfo {

	private String ShippedDate;
	private String ShipVia;
	private String Freight;
	private String ShipName;
	private String ShipAddress;
	private String ShipCity;
	private String ShipRegion;
	private String ShipPostalCode;
	private String ShipCountry;

	@XmlAttribute(name = "ShippedDate")
	public String getShippedDate() {
		return ShippedDate;
	}

	public void setShippedDate(String shippedDate) {
		ShippedDate = shippedDate;
	}

	@XmlElement(name = "ShipVia")
	public String getShipVia() {
		return ShipVia;
	}

	public void setShipVia(String shipVia) {
		ShipVia = shipVia;
	}

	@XmlElement(name = "Freight")
	public String getFreight() {
		return Freight;
	}

	public void setFreight(String freight) {
		Freight = freight;
	}

	@XmlElement(name = "ShipName")
	public String getShipName() {
		return ShipName;
	}

	public void setShipName(String shipName) {
		ShipName = shipName;
	}

	@XmlElement(name = "ShipAddress")
	public String getShipAddress() {
		return ShipAddress;
	}

	public void setShipAddress(String shipAddress) {
		ShipAddress = shipAddress;
	}

	@XmlElement(name = "ShipCity")
	public String getShipCity() {
		return ShipCity;
	}

	public void setShipCity(String shipCity) {
		ShipCity = shipCity;
	}

	@XmlElement(name = "ShipRegion")
	public String getShipRegion() {
		return ShipRegion;
	}

	public void setShipRegion(String shipRegion) {
		ShipRegion = shipRegion;
	}

	@XmlElement(name = "ShipPostalCode")
	public String getShipPostalCode() {
		return ShipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		ShipPostalCode = shipPostalCode;
	}

	@XmlElement(name = "ShipCountry")
	public String getShipCountry() {
		return ShipCountry;
	}

	public void setShipCountry(String shipCountry) {
		ShipCountry = shipCountry;
	}
}
