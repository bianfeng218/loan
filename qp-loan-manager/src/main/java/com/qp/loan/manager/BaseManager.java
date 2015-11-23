package com.qp.loan.manager;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.qp.common.cache.QpCacheUtil;

/**
 * @author haiping
 *
 */
public abstract class BaseManager<T> {
	
	protected @Resource QpCacheUtil qpCacheUtil;
	
	protected Set<String> cacheKeys = new HashSet<String>();
	protected abstract void prepareCacheKeys(T o);
	
	protected void deleteCache(T o){
		prepareCacheKeys(o);
		if(CollectionUtils.isEmpty(cacheKeys)){
			return ;
		}
		for(Iterator<String> it = cacheKeys.iterator();it.hasNext();){
			String key = it.next();
			qpCacheUtil.delete(key);
			it.remove();
		}
	}
}
