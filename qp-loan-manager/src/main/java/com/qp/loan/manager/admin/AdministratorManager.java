package com.qp.loan.manager.admin;

import com.qp.loan.domain.Administrator;

/**
 * @author haiping
 *
 */
public interface AdministratorManager {
	public Administrator findAdminByPin(String pin);
}
