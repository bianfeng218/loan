package com.qp.loan.service.auth.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.qp.common.cache.QpCacheUtil;
import com.qp.common.web.auth.UserType;
import com.qp.loan.domain.Administrator;
import com.qp.loan.manager.admin.AdministratorManager;
import com.qp.loan.service.auth.AuthService;
import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限验证类
 * @author yfliqiang 2015-09-18
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static final Log log = LogFactory.getLog(AuthServiceImpl.class);
    @Autowired
    private QpCacheUtil cacheUtil;
    @Autowired
    private AdministratorManager administratorManager;

    private String[] opArray;//系统中所有的操作，spring注入
    private Map<String,Set<String>> authMap;//各种身份对应的操作，spring注入

    /**
     * 判断某人是否具有某权限
     * @param pin 登录账号
     * @param authCode 权限编号， @see AuthCode 中的常量
     * @return 有权限返回true，没有权限；返回false
     */
    public boolean hasPrivilege(String pin,String authCode){
        UserType userType = this.userType(pin);
        if (this.hasPrivilege(userType, authCode)) {
//    		log.info("用户"+pin+"有权限："+AuthCodeEnum.getAuthNameByCode(authCode));
            return true;
        }else {
//			log.info("用户"+pin+"没有权限："+AuthCodeEnum.getAuthNameByCode(authCode));
            return false;
        }
    }
    /**
     * 根据登录账号获取用户身份，主要用于列表页的操作
     * 如果一个用户存在多种身份,则返回优先级最高的身份。
     * @param pin
     * @return 无法确定身份时返回 @see UserType.CRACKER
     */
    public UserType userType(String pin) {
        Administrator user = administratorManager.findAdminByPin(pin);
        UserType userType = UserType.getUserTypeByCode(user.getType().intValue());
    	log.info("登录用户"+pin+"，身份是:"+userType.getType());
        return userType;
    }
    /**
     * 判断某个用户类型是否具有某个权限
     * @param userType 用户类型
     * @param authCode 权限码， @see AuthCode 中的常量
     * @return
     */
    private boolean hasPrivilege(UserType userType,String authCode){
        if (userType == UserType.CRACKER) {
            return false;
        }
        if (!authMap.containsKey(userType.getType())) {
            log.info("权限配置出错！！！there is no userType "+userType.getType()+" in config file.");
            return false;
        }

        Set<String> userPrivileges = authMap.get(userType.getType());
        return userPrivileges.contains(authCode);
    }


    /**
     * 判断用户是否拥有全部权限，只要有一个权限不满足，则认为他没有操作权限
     * @param pin 用户登录pin
     * @param privileges 权限码列表
     * @return
     */
    public boolean hasPrivilege(String pin, String... privileges) {
        if (null == privileges || privileges.length == 0) {
            log.info("privileges set error.");
            return true;
        }
        //权限码去重
        Set<String> uniquePrivileges = new HashSet<String>(Arrays.asList(privileges));

        for (String authCode:uniquePrivileges){
            if (!this.hasPrivilege(pin, authCode)) {
                log.info(pin+"没有【"+authCode+"】的权限，本操作需要以下权限："+privileges);
                return false;
            }
        }

        return true;
    }

    public void setAuthMap(Map<String, Set<String>> authMap) {
        this.authMap = authMap;
    }

    public void setOpArray(String[] opArray) {
        this.opArray = opArray;
    }
}
