package com.tvdinh.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.tvdinh.dao.GenericDAO;
import com.tvdinh.mapper.RowMapper;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.model.RoleModel;

public class AbstractDAO<T> implements GenericDAO<T> {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

	public Connection getConnection() {
		try {
			/*
			 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); return
			 * DriverManager.getConnection(
			 * "jdbc:sqlserver://localhost:1433;DatabaseName=WebSalesDH;User=dinh;Password=dinh;IntegratedSecurity=true"
			 * );
			 */
			/*
			 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); String url=
			 * "jdbc:sqlserver://localhost:1433;databaseName=WebSalesDH;integratedSecurity=true";
			 * String user="dinh"; String password="dinh";
			 */
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");

			return DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<CustomerModel> findAll() {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<CustomerModel> list = new ArrayList<CustomerModel>();
		String sql = "select customer.*,role.code,role.name as rolename from customer INNER JOIN role on(role.id=customer.roleid)";

		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				CustomerModel cus = new CustomerModel();
				cus.setId(rs.getLong("id"));
				cus.setUsername(rs.getString("username"));
				cus.setEmail(rs.getString("email"));
				cus.setName(rs.getNString("name"));
				cus.setPassword(rs.getString("password"));
				cus.setRoleid(rs.getLong("roleid"));

				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getNString("rolename"));

				cus.setRole(role);

				list.add(cus);
			}

		} catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<T> results=new ArrayList<T>();
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			// setParam
			setParameter(statement, parameters);
			
			rs = statement.executeQuery();
			while(rs.next())
			{
				results.add(rowMapper.mapRow(rs));
			}
			return results;
		} catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return results;
	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object param = parameters[i];
				int index = i + 1;

				if (param instanceof Long) {
					statement.setLong(index, (Long) param);
				}
				else if(param instanceof String)
				{
					statement.setNString(index, (String)param);
				}
				else if(param instanceof Double)
				{
					statement.setDouble(index, (Double)param);
				}
				else if(param instanceof Integer)
				{
					statement.setInt(index, (Integer)param);
				}
				else if(param instanceof Date)
				{
					statement.setDate(index, (java.sql.Date)param);
				}
				else if(param instanceof Timestamp)
				{
					statement.setTimestamp(index, (Timestamp)param);
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int count=0;
		try {
			con = getConnection();
			statement = con.prepareStatement(sql);
			setParameter(statement, parameters);
			
			rs = statement.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);// select count(*) from...
			}	
		} catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
	

}
