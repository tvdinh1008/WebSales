package com.tvdinh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tvdinh.model.CustomerModel;
import com.tvdinh.model.RoleModel;

public class CustomerMapper implements RowMapper<CustomerModel>{

	@Override
	public CustomerModel mapRow(ResultSet rs) {
		try {
			CustomerModel cus=new CustomerModel();
			cus.setId(rs.getLong("id"));
			cus.setName(rs.getNString("name"));
			cus.setUsername(rs.getNString("username"));
			cus.setPassword(rs.getNString("password"));
			cus.setStatus(rs.getInt("status"));
			/*
			cus.setCreatedDate(rs.getTimestamp(""));
			cus.setModifiedDate(rs.getTimestamp(""));
			*/
			cus.setEmail(rs.getString("email"));
			cus.setPhone(rs.getString("phone"));
			cus.setAddress(rs.getNString("address"));
			cus.setRoleid(rs.getLong("roleid"));
			
			try{
				RoleModel role=new RoleModel();
				role.setCode(rs.getNString("code"));
				role.setName(rs.getNString("name_role"));//chú ý as lại tên
				
				cus.setRole(role);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			return cus;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
