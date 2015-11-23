package com.qp.loan.Contants;

/**
 * @author haiping
 *
 */
public class CacheContants {

	/**
	 * 缓存一天
	 */
	public static final int CACHE_ONE_DAY = 86400; 
	/**
	 * 缓存一个月
	 */
	public static final int CACHE_ONE_WEEK = 604800;
	/**
	 * 缓存一个小时
	 */
	public static final int CACHE_ONE_HOUR = 3600;
	/**
	 * 缓存前缀
	 */
	private static final String CACHE_PREFIX = "loan_";
	
	public static String getOverseasCompanyCacheKey(Integer id){
		return CACHE_PREFIX + "overseasCompany_" + id;
	}
	
	public static String getSupplierCacheKey(Integer id){
		return CACHE_PREFIX + "supplier_" + id;
	}
	
	public static String getWareCacheKey(Integer id){
		return CACHE_PREFIX + "ware_" + id;
	}
	
}
