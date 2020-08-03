package com.tvdinh.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.tvdinh.model.CustomerModel;
import com.tvdinh.model.RoleModel;

public class AbstractDAO<T>{
	
	ResourceBundle  resourceBundle=ResourceBundle.getBundle("db");
	
	public Connection getConnection()
	{
		try {
			/*
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=WebSalesDH;User=dinh;Password=dinh;IntegratedSecurity=true");
			*/
			/*
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://localhost:1433;databaseName=WebSalesDH;integratedSecurity=true";
			String user="dinh";
			String password="dinh";
			*/
			Class.forName(resourceBundle.getString("driverName"));
			String url=resourceBundle.getString("url");
			String user=resourceBundle.getString("user");
			String password=resourceBundle.getString("password");
			
			return DriverManager.getConnection(url,user,password);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<CustomerModel> findAll()
	{
		Connection con=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		List<CustomerModel> list=new ArrayList<CustomerModel>();
		String sql="select customer.*,role.code,role.name as rolename from customer INNER JOIN role on(role.id=customer.roleid)";
				
		
		try {
			con=getConnection();
			statement=con.prepareStatement(sql);
			rs=statement.executeQuery();
			while(rs.next())
			{   
				CustomerModel cus=new CustomerModel();
				cus.setId(rs.getLong("id"));
				cus.setUsername(rs.getString("username"));
				cus.setEmail(rs.getString("email"));
				cus.setName(rs.getNString("name"));
				cus.setPassword(rs.getString("password"));
				cus.setRoleid(rs.getLong("roleid"));
				
				RoleModel role=new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getNString("rolename"));
				
				cus.setRole(role);
				
				list.add(cus);
			}
			
			
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
