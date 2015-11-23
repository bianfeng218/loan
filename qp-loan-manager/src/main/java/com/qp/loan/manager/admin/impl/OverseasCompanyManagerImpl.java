package com.qp.loan.manager.admin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qp.loan.Contants.CacheContants;
import com.qp.loan.dao.OverseasCompanyDao;
import com.qp.loan.domain.OverseasCompany;
import com.qp.loan.manager.BaseManager;
import com.qp.loan.manager.admin.OverseasCompanyManager;

/**
 * @author haiping
 * 
 */
@Component
public class OverseasCompanyManagerImpl extends BaseManager<OverseasCompany> implements OverseasCompanyManager{
	
	private @Resource OverseasCompanyDao overseasCompanyDao;
	
	@Override
	public OverseasCompany findOverseasByCached(Integer id) {
		String key = CacheContants.getOverseasCompanyCacheKey(id);
		OverseasCompany obj = (OverseasCompany)qpCacheUtil.get(key);
		if(obj!=null){
			return obj;
		}
		obj = overseasCompanyDao.queryById(id);
		if(obj!=null){
			qpCacheUtil.put(key, obj, CacheContants.CACHE_ONE_HOUR);
		}
		return obj;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(OverseasCompany company) {
		int count = 0;
		count = overseasCompanyDao.update(company);
		deleteCache(company);
		return count;
	}
	
	@Override
	public OverseasCompany findOverseasCompanyByPin(String pin) {
		OverseasCompany query = new OverseasCompany();
		query.setPin(pin);
		List<OverseasCompany> companys = overseasCompanyDao.queryByCondition(query);
		if (CollectionUtils.isNotEmpty(companys)) {
			return companys.get(0);
		}

		return null;
	}

	@Override
	protected void prepareCacheKeys(OverseasCompany o) {
		cacheKeys.add(CacheContants.getOverseasCompanyCacheKey(o.getId()));
	}
}
