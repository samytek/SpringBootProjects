package com.example.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CustomerOrderInfoDAO;
import com.example.demo.utils.CommonUtils;
import com.example.demo.xmlEntities.Address;
import com.example.demo.xmlEntities.Customer;
import com.example.demo.xmlEntities.CustomerOrderInfo;
import com.example.demo.xmlEntities.Customers;
import com.example.demo.xmlEntities.Order;
import com.example.demo.xmlEntities.Orders;
import com.example.demo.xmlEntities.ShipInfo;
import com.example.demo.xmlEntities.ShoppingInfo;

@Service
public class XmlFileParsingServiceImpl implements XmlFileParsingService {

	@Autowired
	CustomerOrderInfoDAO customerOrderInfoDAO;

	public static ArrayList<Integer> resultIdList = new ArrayList<Integer>();

	@Override
	public void parseXmlToJavaObject() throws Exception {
		File file = new File("C:\\Users\\Asus\\Desktop\\xmlFileDemo.xml");
		ShoppingInfo shoppingInfoInst = unmarshalXMLToEmployees(file);
		Customers customersInst = shoppingInfoInst.getCustomers();
		Orders orders = shoppingInfoInst.getOrders();

		List<Customer> customerList = customersInst.getCustomer();
		List<Order> orderList = orders.getOrder();

		showDetails(customerList, orderList);
		showOrderDetails(orderList);
		resultIdList.clear();
	}

	public void showDetails(List<Customer> customerList, List<Order> orderList) {
		List<Address> addressList = new ArrayList<Address>();
		for (Customer customerInst : customerList) {
			CustomerOrderInfo customerOrderInfoInst = new CustomerOrderInfo();
			customerOrderInfoInst.setCustomerID(customerInst.getCustomerID());
			customerOrderInfoInst.setCompanyName(customerInst.getCompanyName());
			customerOrderInfoInst.setContactName(customerInst.getContactName());
			customerOrderInfoInst.setContactTitle(customerInst.getContactTitle());
			customerOrderInfoInst.setPhone(customerInst.getPhone());
			addressList.addAll(customerInst.getAddress());
			for (Address addressInst : addressList) {
				customerOrderInfoInst.setCity(addressInst.getCity());
				customerOrderInfoInst.setCountry(addressInst.getCountry());
				customerOrderInfoInst.setPostalCode(addressInst.getPostalCode());
				customerOrderInfoInst.setRegion(addressInst.getRegion());
				customerOrderInfoInst.setAddress(addressInst.getAddress());
			}
			CustomerOrderInfo customerOrderInfo = customerOrderInfoDAO.save(customerOrderInfoInst);
			resultIdList.add(customerOrderInfo.getId());
		}
	}

	public void showOrderDetails(List<Order> orderList) {
		List<ShipInfo> shipInfoList = new ArrayList<ShipInfo>();
		for (Integer resultId : resultIdList) {
			for (Order orderInst : orderList) {
				CustomerOrderInfo customerOrderInfoInst = customerOrderInfoDAO.findByIdAndCustomerID(resultId,	orderInst.getCustomerID());
				if (CommonUtils.isNotNullAndNotEmpty(customerOrderInfoInst)) {
					customerOrderInfoInst.setEmployeeID(orderInst.getEmployeeID());
					customerOrderInfoInst.setOrderDate(orderInst.getOrderDate());
					customerOrderInfoInst.setRequiredDate(orderInst.getRequiredDate());
					shipInfoList.addAll(orderInst.getShipInfo());
					for (ShipInfo shipInfoInst : shipInfoList) {
						customerOrderInfoInst.setShippedDate(shipInfoInst.getShippedDate());
						customerOrderInfoInst.setShipVia(shipInfoInst.getShipVia());
						customerOrderInfoInst.setShipName(shipInfoInst.getShipName());
						customerOrderInfoInst.setShipAddress(shipInfoInst.getShipAddress());
						customerOrderInfoInst.setShipCity(shipInfoInst.getShipCity());
						customerOrderInfoInst.setShipCountry(shipInfoInst.getShipCountry());
						customerOrderInfoInst.setShipRegion(shipInfoInst.getShipRegion());
						customerOrderInfoInst.setShipPostalCode(shipInfoInst.getShipPostalCode());
						customerOrderInfoInst.setFreight(shipInfoInst.getFreight());
						customerOrderInfoDAO.save(customerOrderInfoInst);
					}
				}
			}
		}
	}

	private ShoppingInfo unmarshalXMLToEmployees(File file) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(ShoppingInfo.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (ShoppingInfo) jaxbUnmarshaller.unmarshal(file);
	}
}
