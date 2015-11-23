package com.qp.loan.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.qp.common.dao.BaseDaoImpl;
import com.qp.loan.dao.WareDao;
import com.qp.loan.domain.Ware;

/**
 * @author haiping
 *
 */
@Repository
@SuppressWarnings({"unchecked","deprecation"})
public class WareDaoImpl extends BaseDaoImpl implements WareDao{
	
	private static final String ADD = "Ware.add";
	private static final String UPDATE = "Ware.update";
	private static final String QUERY_BY_ID = "Ware.queryById";
	private static final String COUNT = "Ware.countByCondition";
	private static final String QUERY_BY_CONDITION = "Ware.queryByCondition";
	private static final String DELETE = "Ware.delete";

	public Integer add(Ware o) {
		return (Integer) this.insert(ADD, o);
	}

	public int update(Ware o) {
		return this.update(UPDATE, o);
	}

	public Ware queryById(Integer id) {
		return (Ware) this.queryForObject(QUERY_BY_ID, id);
	}

	public int countByCondition(Ware o) {
		return (Integer) this.queryForObject(COUNT, o);
	}

	public List<Ware> queryByCondition(Ware o) {
		return this.queryForList(QUERY_BY_CONDITION, o);
	}

	public int delete(Integer id) {
		return this.delete(DELETE, id);
	}
}
