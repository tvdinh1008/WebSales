package com.tvdinh.service;

import java.util.List;

import com.tvdinh.model.CustomerModel;

public interface ICustomerService {
	
	 CustomerModel findByUserNameandPasswordAndStatus(String username,String password,Integer status);
	 List<CustomerModel> findAll();
}
