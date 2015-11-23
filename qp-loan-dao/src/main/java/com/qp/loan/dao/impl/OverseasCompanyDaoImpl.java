package com.qp.loan.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.qp.common.dao.BaseDaoImpl;
import com.qp.loan.dao.OverseasCompanyDao;
import com.qp.loan.domain.OverseasCompany;

/**
 * @author haiping
 *
 */
@Repository
@SuppressWarnings({"unchecked","deprecation"})
public class OverseasCompanyDaoImpl extends BaseDaoImpl implements OverseasCompanyDao{
	
	private static final String ADD = "OverseasCompany.add";
	private static final String UPDATE = "OverseasCompany.update";
	private static final String QUERY_BY_ID = "OverseasCompany.queryById";
	private static final String COUNT = "OverseasCompany.countByCondition";
	private static final String QUERY_BY_CONDITION = "OverseasCompany.queryByCondition";
	private static final String DELETE = "OverseasCompany.delete";

	public Integer add(OverseasCompany o) {
		return (Integer) this.insert(ADD, o);
	}

	public int update(OverseasCompany o) {
		return this.update(UPDATE, o);
	}

	public OverseasCompany queryById(Integer id) {
		return (OverseasCompany) this.queryForObject(QUERY_BY_ID, id);
	}

	public int countByCondition(OverseasCompany o) {
		return (Integer) this.queryForObject(COUNT, o);
	}

	public List<OverseasCompany> queryByCondition(OverseasCompany o) {
		return this.queryForList(QUERY_BY_CONDITION, o);
	}

	public int delete(Integer id) {
		return this.delete(DELETE, id);
	}
}
