package com.qp.loan.service.auth;

import com.qp.common.web.auth.UserType;

/**
 * 权限相关操作类
 * 获取用户所属应用，判断用户是否具有某权限，均由此类负责；
* @author yfliqiang 2015-09-19
*
*/
public interface AuthService {
    /**
     * 判断某人是否具有某权限
     * @param pin 登录账号
     * @param authCode 权限编号， @see AuthCode 中的常量
     * @return 有权限返回true，没有权限；返回false
     */
    public boolean hasPrivilege(String pin, String authCode);
    /**
     * 根据登录账号获取用户身份
     * @param pin
     * @return 无法确定身份时返回 @see UserType.CRACKER
     */
    public UserType userType(String pin);
    /**
     * 判断用户是否拥有全部权限，只要有一个权限不满足，则认为他没有操作权限
     * @param pin 用户登录pin
     * @param privileges 权限码列表
     * @return
     */
    public boolean hasPrivilege(String pin, String... privileges);
}