package com.example.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
public class GenerateXmlFileFromObjectServiceImpl implements GenerateXmlFileFromObjectService {

	@Autowired
	CustomerOrderInfoDAO customerOrderInfoDAO;

	@Override
	public void XmlFileFromObject() throws Exception {
		List<CustomerOrderInfo> customerOrderInfoList = customerOrderInfoDAO.findAll();

		Customers customers = new Customers();
		List<Customer> customerList = new ArrayList<>();
		Orders orders = new Orders();
		List<Order> orderList = new ArrayList<>();
		if (CommonUtils.isNotNullAndNotEmpty(customerOrderInfoList)) {
			for (CustomerOrderInfo customerOrderInfoInst : customerOrderInfoList) {
				Customer customer = new Customer();
				customer.setCustomerID(customerOrderInfoInst.getCustomerID());
				customer.setCompanyName(customerOrderInfoInst.getCompanyName());
				customer.setContactName(customerOrderInfoInst.getContactName());
				customer.setContactTitle(customerOrderInfoInst.getContactTitle());
				customer.setPhone(customerOrderInfoInst.getPhone());

				Address address = new Address();
				address.setAddress(customerOrderInfoInst.getAddress());
				address.setCity(customerOrderInfoInst.getCity());
				address.setCountry(customerOrderInfoInst.getCountry());
				address.setPostalCode(customerOrderInfoInst.getPostalCode());
				address.setRegion(customerOrderInfoInst.getRegion());
				List<Address> addressList = new ArrayList<>();
				addressList.add(address);
				customer.setAddress(addressList);

				customerList.add(customer);
				customers.setCustomer(customerList);

				Order order = new Order();
				order.setCustomerID(customerOrderInfoInst.getCustomerID());
				order.setEmployeeID(customerOrderInfoInst.getEmployeeID());
				order.setOrderDate(customerOrderInfoInst.getOrderDate());
				order.setRequiredDate(customerOrderInfoInst.getRequiredDate());

				ShipInfo shipInfo = new ShipInfo();
				shipInfo.setShippedDate(customerOrderInfoInst.getShippedDate());
				shipInfo.setShipVia(customerOrderInfoInst.getShipVia());
				shipInfo.setFreight(customerOrderInfoInst.getFreight());
				shipInfo.setShipName(customerOrderInfoInst.getShipName());
				shipInfo.setShipAddress(customerOrderInfoInst.getShipAddress());
				shipInfo.setShipCity(customerOrderInfoInst.getShipCity());
				shipInfo.setShipCountry(customerOrderInfoInst.getShipCountry());
				shipInfo.setShipRegion(customerOrderInfoInst.getShipRegion());
				shipInfo.setShipPostalCode(customerOrderInfoInst.getShipPostalCode());

				List<ShipInfo> shipInfoList = new ArrayList<>();
				shipInfoList.add(shipInfo);
				order.setShipInfo(shipInfoList);

				orderList.add(order);
				orders.setOrder(orderList);

				ShoppingInfo shoppingInfo = new ShoppingInfo();
				shoppingInfo.setCustomers(customers);
				shoppingInfo.setOrders(orders);
				marshalPersonToXML(shoppingInfo);
			}
		}
		System.out.println("Done");
	}

	private void marshalPersonToXML(ShoppingInfo shoppingInfo) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ShoppingInfo.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(shoppingInfo, new File("C:\\Users\\Asus\\Desktop\\xmlFileDemo.xml"));
	}
}
