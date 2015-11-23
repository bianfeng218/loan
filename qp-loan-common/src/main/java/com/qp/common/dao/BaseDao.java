package com.qp.common.dao;


import java.util.List;

public interface BaseDao<T> {
	public int delete(Integer id);
	public Integer add(T o);
	public int update(T o);
	public T queryById(Integer id);
	public int countByCondition(T o);
	public List<T> queryByCondition(T o);
}