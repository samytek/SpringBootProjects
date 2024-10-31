package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.xmlEntities.CustomerOrderInfo;

public interface CustomerOrderInfoDAO extends JpaRepository<CustomerOrderInfo, Long>{

	public CustomerOrderInfo findById(int id);
	
	public CustomerOrderInfo findByIdAndCustomerID(int id, String str);

	public List<CustomerOrderInfo> findAll();

}
