package com.tvdinh.dao.impl;

import java.util.List;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.mapper.CustomerMapper;
import com.tvdinh.model.CustomerModel;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO{

	@Override
	public CustomerModel findByUserNameandPasswordAndStatus(String username, String password, String status) {
		
		
		return null;
	}
	
	@Override
	public List<CustomerModel> findAll()
	{
		String sql = "select customer.*,role.code,role.name as name_role from customer INNER JOIN role on(role.id=customer.roleid)";
		
		List<CustomerModel> list=query(sql, new CustomerMapper());
		return list;	
	}
	

	
}
