package com.qp.loan.manager.admin;

import com.qp.common.base.PaginatedArrayList;
import com.qp.loan.domain.Ware;

/**
 * @author haiping
 *
 */
public interface WareManager {

	public PaginatedArrayList<Ware> list(Ware query);
	public Ware findWareByIdCached(Integer ware);
	public int update(Ware ware);
}
