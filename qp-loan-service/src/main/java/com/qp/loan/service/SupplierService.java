package com.qp.loan.service;

import com.qp.common.base.Message;
import com.qp.loan.domain.Supplier;

/**
 * @author haiping
 *
 */
public interface SupplierService {

	public Message create(Supplier supplier);
	public Message validateLogin(String pin,String password);
	public Message enroll(Supplier supplier);
}
