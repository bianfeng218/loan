package com.qp.loan.form;

import org.apache.commons.lang.StringUtils;

import com.qp.common.base.Message;


/**
 * @author haiping
 *
 */
public class LoginForm {

	private String loginName;
	private String password;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Message validate4Login(){
		if(StringUtils.isBlank(loginName)){
			return Message.failure("登录名为空！");
		}
		if(StringUtils.isBlank(password)){
			return Message.failure("密码为空！");
		}
		return Message.success();
	}
}
