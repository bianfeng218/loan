package com.qp.loan.service;


import org.springframework.ui.ModelMap;

import com.qp.common.base.Message;
import com.qp.loan.domain.OverseasCompany;

/**
 * @author haiping
 *
 */
public interface OverseasCompanyService {

	public Message create(OverseasCompany company);
	public Message validateLogin(String pin,String password);
	public Message enroll(OverseasCompany company);
}
