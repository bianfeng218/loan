package com.qp.loan.manager.admin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qp.loan.Contants.CacheContants;
import com.qp.loan.dao.SupplierDao;
import com.qp.loan.domain.Supplier;
import com.qp.loan.manager.BaseManager;
import com.qp.loan.manager.admin.SupplierManager;

/**
 * @author haiping
 * 
 */
@Component
public class SupplierManagerImpl extends BaseManager<Supplier> implements SupplierManager{
	
	private @Resource SupplierDao supplierDao;
	
	@Override
	public Supplier findSupplierByCached(Integer id) {
		String key = CacheContants.getSupplierCacheKey(id);
		Supplier obj = (Supplier)qpCacheUtil.get(key);
		if(obj!=null){
			return obj;
		}
		obj = supplierDao.queryById(id);
		if(obj!=null){
			qpCacheUtil.put(key, obj, CacheContants.CACHE_ONE_HOUR);
		}
		return obj;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(Supplier supplier) {
		int count = 0;
		count = supplierDao.update(supplier);
		deleteCache(supplier);
		return count;
	}
	
	@Override
	public Supplier findSupplierByPin(String pin) {
		Supplier query = new Supplier();
		query.setPin(pin);
		List<Supplier> suppliers = supplierDao.queryByCondition(query);
		if (CollectionUtils.isNotEmpty(suppliers)) {
			return suppliers.get(0);
		}

		return null;
	}
	
	@Override
	protected void prepareCacheKeys(Supplier o) {
		cacheKeys.add( CacheContants.getSupplierCacheKey(o.getId()));
	}
}
