package com.tvdinh.dao;

import java.util.List;

import com.tvdinh.mapper.RowMapper;

public interface GenericDAO<T>{
	
	List<T> query(String sql,RowMapper<T> rowMapper,Object ... parameters);
	
	int count(String sql, Object...parameters );
}
