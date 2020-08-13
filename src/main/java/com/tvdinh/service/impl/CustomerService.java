package com.tvdinh.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.paging.Pageble;
import com.tvdinh.service.ICustomerService;

public class CustomerService implements ICustomerService{
	@Inject
	private ICustomerDAO customerDAO;
	
	@Override
	public List<CustomerModel> findAll() {
		return customerDAO.findAll();
	}

	@Override
	public CustomerModel findByUserNameandPasswordAndStatus(String username, String password, Integer status) {
		return customerDAO.findByUserNameandPasswordAndStatus(username, password, status);
	}

	@Override
	public int getTotalItem() {
		return customerDAO.getTotalItem();
	}

	@Override
	public List<CustomerModel> findAll(Integer offset, Integer limit) {
		return customerDAO.findAll(offset, limit);
	}

	@Override
	public List<CustomerModel> findAll(Pageble pageble) {
		return customerDAO.findAll(pageble);
	}

	@Override
	public CustomerModel findOnde(Long id) {
		return customerDAO.findOne(id);
	}

}
