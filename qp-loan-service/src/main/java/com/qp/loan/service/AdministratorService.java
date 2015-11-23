package com.qp.loan.service;

import com.qp.common.base.Message;

/**
 * @author haiping
 *
 */
public interface AdministratorService {
	public Message validateAdmin(String pin,String password);
}
