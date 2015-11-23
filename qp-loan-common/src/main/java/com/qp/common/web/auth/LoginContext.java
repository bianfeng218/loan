package com.qp.common.web.auth;



/**
 * @author haiping
 *
 */
public class LoginContext {
	
	private static final ThreadLocal<LoginContext> holder = new ThreadLocal<LoginContext>();

	private Integer id;
	private String pin;
	private Short type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public static ThreadLocal<LoginContext> getHolder() {
		return holder;
	}

	public static void setLoginContext(LoginContext loginContext){
		holder.set(loginContext);
	}
	
	public static void remove(){
		holder.remove();
	}
	
	public static LoginContext getLoginContext(){
		return holder.get();
	}
}
