package com.qp.loan.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.qp.common.dao.BaseDaoImpl;
import com.qp.loan.dao.SupplierDao;
import com.qp.loan.domain.Supplier;

/**
 * @author haiping
 *
 */
@Repository
@SuppressWarnings({"unchecked","deprecation"})
public class SupplierDaoImpl extends BaseDaoImpl implements SupplierDao{
	
	private static final String ADD = "Supplier.add";
	private static final String UPDATE = "Supplier.update";
	private static final String QUERY_BY_ID = "Supplier.queryById";
	private static final String COUNT = "Supplier.countByCondition";
	private static final String QUERY_BY_CONDITION = "Supplier.queryByCondition";
	private static final String DELETE = "Supplier.delete";

	public Integer add(Supplier o) {
		return (Integer) this.insert(ADD, o);
	}

	public int update(Supplier o) {
		return this.update(UPDATE, o);
	}

	public Supplier queryById(Integer id) {
		return (Supplier) this.queryForObject(QUERY_BY_ID, id);
	}

	public int countByCondition(Supplier o) {
		return (Integer) this.queryForObject(COUNT, o);
	}

	public List<Supplier> queryByCondition(Supplier o) {
		return this.queryForList(QUERY_BY_CONDITION, o);
	}

	public int delete(Integer id) {
		return this.delete(DELETE, id);
	}
}
