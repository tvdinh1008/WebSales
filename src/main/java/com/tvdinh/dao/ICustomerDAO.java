package com.tvdinh.dao;

import java.util.List;

import com.tvdinh.model.CustomerModel;


public interface ICustomerDAO extends GenericDAO<CustomerModel>{
	CustomerModel findByUserNameandPasswordAndStatus(String username, String password, String status);
	List<CustomerModel> findAll();
}
