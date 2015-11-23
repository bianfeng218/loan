package com.qp.loan.manager.admin;

import com.qp.loan.domain.Supplier;

/**
 * @author haiping
 *
 */
public interface SupplierManager {

	public Supplier findSupplierByPin(String pin);
	public Supplier findSupplierByCached(Integer id);
	public int update(Supplier cpmpany);
}
