package com.qp.loan.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.qp.common.dao.BaseDaoImpl;
import com.qp.loan.dao.AdministratorDao;
import com.qp.loan.domain.Administrator;

/**
 * @author haiping
 *
 */
@Repository
@SuppressWarnings({"unchecked","deprecation"})
public class AdministratorDaoImpl extends BaseDaoImpl implements AdministratorDao{
	
	private static final String ADD = "Administrator.add";
	private static final String UPDATE = "Administrator.update";
	private static final String QUERY_BY_ID = "Administrator.queryById";
	private static final String COUNT = "Administrator.countByCondition";
	private static final String QUERY_BY_CONDITION = "Administrator.queryByCondition";
	private static final String DELETE = "Administrator.delete";

	public Integer add(Administrator o) {
		return (Integer) this.insert(ADD, o);
	}

	public int update(Administrator o) {
		return this.update(UPDATE, o);
	}

	public Administrator queryById(Integer id) {
		return (Administrator) this.queryForObject(QUERY_BY_ID, id);
	}

	public int countByCondition(Administrator o) {
		return (Integer) this.queryForObject(COUNT, o);
	}

	public List<Administrator> queryByCondition(Administrator o) {
		return this.queryForList(QUERY_BY_CONDITION, o);
	}

	public int delete(Integer id) {
		return this.delete(DELETE, id);
	}
}
