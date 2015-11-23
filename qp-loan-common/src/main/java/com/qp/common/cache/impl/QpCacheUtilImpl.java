package com.qp.common.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.qp.common.cache.QpCacheUtil;

@Component
public class QpCacheUtilImpl implements QpCacheUtil{
	 
    /**
     * 存储map
     */
    private static Map<String, CacheObject> map = new ConcurrentHashMap<String, CacheObject>();
    
    @Override
    public void put(String key, Object o, int expire) {
        map.put(key, new CacheObject(System.currentTimeMillis() + expire * 1000, o));
    }
    
    @Override
    public void delete(String key) {
        map.remove(key);
    }
    
    @Override
    public Object get(String key) {
        Object rs = null;
        CacheObject o = map.get(key);
        if (null != o) {
            long currentTime = System.currentTimeMillis();
            if (currentTime < o.getExpireTime()) {
                rs = o.getObject();
            }
        }
        
        return rs;
    }
    
    /**
     * 存储内容
     * 
     * @author GaoXiang Date: 2015-4-21
     */
    private static class CacheObject {
        
        /**
         * 缓存的过期时间
         */
        private long expireTime;
        
        /**
         * 存储的数据
         */
        private Object object;
        
        public CacheObject(long expireTime, Object object) {
            this.expireTime = expireTime;
            this.object = object;
        }
        
        public long getExpireTime() {
            return expireTime;
        }
        
        public Object getObject() {
            return object;
        }
        
    }
}
