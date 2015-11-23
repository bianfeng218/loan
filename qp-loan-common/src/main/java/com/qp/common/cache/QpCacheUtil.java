package com.qp.common.cache;

public interface QpCacheUtil {
	 /**
     * 放入缓存
     * 
     * @param key
     * @param o
     * @param expire 过期时间，单位：秒
     */
    public void put(String key, Object o, int expire);
    
    /**
     * 从缓存删除
     * 
     * @param key
     */
    public void delete(String key);
    
    /**
     * 从缓存取出数据
     * 
     * @param key
     * @return
     */
    public Object get(String key);
}
