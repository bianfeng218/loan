package com.qp.loan.domain;

import java.io.Serializable;
import java.util.Date;

import com.qp.common.base.PaginateBaseDO;

/**
 * @author haiping
 *
 */
public class Administrator extends PaginateBaseDO implements Serializable{
	
	public enum AdminType{
		
		SUPER((short)1,"超级管理员");
		
		private AdminType(short code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		private short code;
		private String desc;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String pin;
	private Short type;
	private String name;
	private String password;
	private Short state;
	private String remark;
	private Date created;
	private Date modified;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	
	
}
