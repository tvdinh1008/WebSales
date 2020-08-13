package com.tvdinh.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.mapper.CustomerMapper;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.paging.Pageble;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO{

	@Override
	public CustomerModel findByUserNameandPasswordAndStatus(String username, String password, Integer status) {
		
		String sql="select customer.*,role.code,role.name as name_role from customer INNER JOIN role on(role.id=customer.roleid) "
				+ "where username=? and password=? and status=?";
		List<CustomerModel> list=query(sql, new CustomerMapper(), username,password,status);
		return list.isEmpty()?null:list.get(0);
	}
	
	@Override
	public List<CustomerModel> findAll()
	{
		String sql = "select customer.*,role.code,role.name as name_role from customer INNER JOIN role on(role.id=customer.roleid)";
		
		List<CustomerModel> list=query(sql, new CustomerMapper());
		return list;	
	}

	@Override
	public int getTotalItem() {
		String sql="select count(*) from customer";
		return count(sql);
	}

	@Override
	public List<CustomerModel> findAll(Integer offset, Integer limit) {
		String sql = "select customer.*,role.code,role.name as name_role from customer INNER JOIN role on(role.id=customer.roleid) ORDER BY customer.name OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY";
		
		List<CustomerModel> list=query(sql, new CustomerMapper(),offset,limit);
		return list;
	}

	@Override
	public List<CustomerModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("select customer.*,role.code,role.name as name_role from customer INNER JOIN role on(role.id=customer.roleid)");
		
		if(pageble.getSorter()!=null && StringUtils.isNotBlank(pageble.getSorter().getSortName())&& StringUtils.isNotBlank(pageble.getSorter().getSortBy()))
		{
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+ pageble.getSorter().getSortBy());
			if(pageble.getPage()!=null && pageble.getOffset() !=null && pageble.getLimit()!=null)
			{
				sql.append(" OFFSET "+pageble.getOffset()+" ROWS FETCH NEXT "+pageble.getLimit()+" ROWS ONLY");
			}
		}
		else if(pageble.getPage()!=null && pageble.getOffset() !=null && pageble.getLimit()!=null)
		{
			sql.append(" ORDER BY customer.name OFFSET "+pageble.getOffset()+" ROWS FETCH NEXT "+pageble.getLimit()+" ROWS ONLY");
		}
		
		
		List<CustomerModel> list=query(sql.toString(), new CustomerMapper());
		return list;
	}

	@Override
	public CustomerModel findOne(Long id) {
		String sql="select customer.*,role.code,role.name as name_role from customer INNER JOIN role on(role.id=customer.roleid) "
				+ "where customer.id=?";
		List<CustomerModel> list=query(sql, new CustomerMapper(), id);
		return list.isEmpty()?null:list.get(0);
	}
	

	
}
