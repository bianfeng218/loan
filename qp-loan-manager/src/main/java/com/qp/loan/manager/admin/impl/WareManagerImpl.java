package com.qp.loan.manager.admin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qp.common.base.PaginatedArrayList;
import com.qp.loan.Contants.CacheContants;
import com.qp.loan.dao.WareDao;
import com.qp.loan.domain.Ware;
import com.qp.loan.manager.BaseManager;
import com.qp.loan.manager.admin.WareManager;

/**
 * @author haiping
 * 
 */
public class WareManagerImpl extends BaseManager<Ware> implements WareManager {
	
	private static final Log log = LogFactory.getLog(WareManagerImpl.class);
	private @Resource WareDao wareDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(Ware ware) {
		int count = 0;
		count = wareDao.update(ware);
		deleteCache(ware);
		return count;
	}
	
	@Override
	public Ware findWareByIdCached(Integer id) {
		String key = CacheContants.getWareCacheKey(id);
		Ware ware = (Ware)qpCacheUtil.get(key);
		if(ware!=null){
			return ware;
		}
		ware = wareDao.queryById(id);
		if(ware!=null){
			qpCacheUtil.put(key, ware, CacheContants.CACHE_ONE_HOUR);
		}
		return ware;
	}
	
	@Override
	public PaginatedArrayList<Ware> list(Ware query) {
		
		PaginatedArrayList<Ware> paginateList = new PaginatedArrayList<Ware>(query.getIndex(), query.getPageSize());
		
		try {
			int total = wareDao.countByCondition(query);
			paginateList.setTotalItem(total);
			query.setTotalItem(total);
			List<Ware> wares = wareDao.queryByCondition(query);
			paginateList.addAll(wares);
		} catch (Exception e) {
			log.error("db error!", e);
		}
		
		return paginateList;
	}

	@Override
	protected void prepareCacheKeys(Ware o) {
		cacheKeys.add(CacheContants.getWareCacheKey(o.getId()));
	}
}
