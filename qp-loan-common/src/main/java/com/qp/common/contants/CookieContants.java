package com.qp.common.contants;

/**
 * @author haiping
 *
 */
public class CookieContants {
	 /** 用户登陆信息cookie key 开始 */
    public static final String USER_ID = "userId";
    
    public static final String USER_ACCOUNT = "userAccount";
    
    public static final String USER_NICK = "userNick";
    
    public static final String LOGIN_TIME = "loginTime";
    
    public static final String BU2X_STRING = "bu2xString";
    
    public static final String LOGIN_STATUS = "loginStatus";
    
    /** 用户登陆信息cookie key 结束 */
    
    // ********* Man(后端运营用户) ********************************************
    
    public static final String SYS_ID = "dtApiMan";
    
    public static final String SYS_USER_PIN = "sysUserPin";
    
    public static final String SYS_LOGIN_TIME = "sysLoginTime";
    
    public static final String CON_TYPE = "conType";
    public static final int LOGIN_COOKIE_AGE = 3;
    
    /** 登陆成功后加密后的数据字符串 */
    public static final String SYS_GBT = "sysGbt";
    
    
    // ********* web(前端运营用户) ********************************************
    
    public static final String WEB_ID = "dtApiWeb";
    
    public static final String WEB_USER_PIN = "webUserPin";
    
    public static final String WEB_LOGIN_TIME = "webLoginTime";
    
    /** 登陆成功后加密后的数据字符串 */
    public static final String WEB_GBT = "webGbt";
    
    public static final String ID = "id";
    public static final String USER_PIN = "userPin";
    public static final String LOGIN_USER_NAME = "loginUserName";
    
    
    public static final String LOGIN_TIME_FOMART = "yyyyMMddHHmmss";
    
    /**
     * cookie保存时间
     */
    public static final int COOKIE_SAVE_TIME = 3 * 24 * 60 * 60;
    
}
