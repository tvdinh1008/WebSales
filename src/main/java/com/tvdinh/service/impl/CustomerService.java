package com.tvdinh.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.service.ICustomerService;

public class CustomerService implements ICustomerService{

	
	@Inject
	private ICustomerDAO customerDAO;
	
	@Override
	public List<CustomerModel> findAll() {
		// TODO Auto-generated method stub
		return customerDAO.findAll();
	}

	@Override
	public CustomerModel findByUserNameandPasswordAndStatus(String username, String password, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

}
