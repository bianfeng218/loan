package com.qp.loan.manager.admin;

import com.qp.loan.domain.OverseasCompany;

/**
 * @author haiping
 *
 */
public interface OverseasCompanyManager {

	public OverseasCompany findOverseasCompanyByPin(String pin);
	public OverseasCompany findOverseasByCached(Integer id);
	public int update(OverseasCompany cpmpany);
}
