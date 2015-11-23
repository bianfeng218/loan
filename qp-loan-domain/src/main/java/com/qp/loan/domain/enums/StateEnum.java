package com.qp.loan.domain.enums;

/**
 * @author haiping
 *
 */
public enum StateEnum {
	AVAILABLE((short)1,"可用"),
	UNAVAILABLE((short)0,"不可用");

	private short code;
	private String desc;
	
	StateEnum(short code,String desc){
		this.code = code;
		this.desc =  desc;
	}
	public short getCode() {
		return code;
	}
	public void setCode(short code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}

