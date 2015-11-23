package com.qp.loan.domain;

import java.io.Serializable;
import java.util.Date;

import com.qp.common.base.PaginateBaseDO;

/**
 * @author haiping
 *
 */
public class OverseasCompany extends PaginateBaseDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String pin;
	private String password;
	private String mobile;
	private String phone;
	private String email;
	private String name;
	private String address;
	private String district;
	private String repertory;
	private String repertoryAddress;
	private String linkman;
	private Short state;
	private String amazonAccount;
	private String ebayAccount;
	private String remark;
	private Date registerTime;
	private Date modified;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public String getAmazonAccount() {
		return amazonAccount;
	}
	public void setAmazonAccount(String amazonAccount) {
		this.amazonAccount = amazonAccount;
	}
	public String getEbayAccount() {
		return ebayAccount;
	}
	public void setEbayAccount(String ebayAccount) {
		this.ebayAccount = ebayAccount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getRepertory() {
		return repertory;
	}
	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}
	public String getRepertoryAddress() {
		return repertoryAddress;
	}
	public void setRepertoryAddress(String repertoryAddress) {
		this.repertoryAddress = repertoryAddress;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
