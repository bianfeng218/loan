package com.qp.common.web.auth;

/**
 * 系统中的用户身份类型
 * @author yfliqiang
 * 2015/09/18
 */
public enum UserType {
	ROOT(1,"系统管理员",1),
	APP_MANAGER(2,"服务商",2),
	APP_OP_USER(3,"信贷公司",3),
	CRACKER(100,"陌生人",100);//防止程序抛出空指针，比如未登录的情况下
	private int code;//数据库中的type
	private String type;//程序中定义的，对应数据库
	private int priority;//身份优先级，如果一个用户有多种身份，那么取优先级最高的那个
	
	public int getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public int getPriority() {
		return priority;
	}

	private UserType(int code, String type, int priority) {
		this.code = code;
		this.type = type;
		this.priority = priority;
	}

	/**
	 * 通过编号获取用户身份
	 * @param code
	 * @return
	 */
	public static final UserType getUserTypeByCode(int code){
		for (UserType userType : UserType.values()) {
			if (code == userType.getCode()) {
				return userType;
			}
		}
		return null;
	}
}
