package com.tvdinh.dao;

import java.util.List;

import com.tvdinh.model.CustomerModel;
import com.tvdinh.paging.Pageble;


public interface ICustomerDAO extends GenericDAO<CustomerModel>{
	CustomerModel findByUserNameandPasswordAndStatus(String username, String password, Integer status);
	List<CustomerModel> findAll();
	int getTotalItem();
	List<CustomerModel> findAll(Integer offset,Integer limit);
	List<CustomerModel> findAll(Pageble pageble);
	CustomerModel findOne(Long id);
}
