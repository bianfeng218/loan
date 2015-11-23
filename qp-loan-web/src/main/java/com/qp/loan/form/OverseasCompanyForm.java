package com.qp.loan.form;

import com.qp.common.base.Message;
import com.qp.common.utils.QpStringUtil;
import com.qp.loan.domain.OverseasCompany;

/**
 * @author haiping
 *
 */
public class OverseasCompanyForm {

	private String pin;
	private String password;
	private String confirmPassword;
	private String email;
	private String name;
	private String address;
	private String district;
	private String repertory;
	private String repertoryAddress;
	private String linkman;
	private String mobile;
	private String phone;
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Message validate4Register(){
		if(QpStringUtil.isEmpty(pin)){
			return Message.failure("用户名为空！");
		}
		if(QpStringUtil.isEmpty(password)){
			return Message.failure("密码为空！");
		}
		if(QpStringUtil.isEmpty(confirmPassword)){
			return Message.failure("确认密码为空！");
		}
		if(!password.equals(confirmPassword)){
			return Message.failure("确认密码不一致！");
		}
		
		OverseasCompany company = convertForm4Register();
		return Message.success()
				.addModel("obj", company);
	}
	
	public Message validate4Enroll(){
		if(QpStringUtil.isEmpty(email)){
			return Message.failure("邮箱为空！");
		}
		if(QpStringUtil.isEmpty(name)){
			return Message.failure("企业名称为空！");
		}
		if(QpStringUtil.isEmpty(address)){
			return Message.failure("企业所在地为空！");
		}
		if(QpStringUtil.isEmpty(address)){
			return Message.failure("企业所在区/县、镇为空！");
		}
		if(QpStringUtil.isEmpty(repertory)){
			return Message.failure("海外仓库名称为空！");
		}
		if(QpStringUtil.isEmpty(repertoryAddress)){
			return Message.failure("海外仓库地址为空！");
		}
		if(QpStringUtil.isEmpty(linkman)){
			return Message.failure("联系人为空！");
		}
		if(QpStringUtil.isEmpty(mobile)){
			return Message.failure("手机为空！");
		}
		if(QpStringUtil.isEmpty(phone)){
			return Message.failure("固定电话为空！");
		}
		
		OverseasCompany company = convertForm4Enroll();
		return Message.success()
				.addModel("obj", company);
	}
	
	private OverseasCompany convertForm4Register(){
		OverseasCompany company = new OverseasCompany();
		company.setPin(pin);
		company.setPassword(password);
		return company;
	}
	
	private OverseasCompany convertForm4Enroll(){
		OverseasCompany company = new OverseasCompany();
		company.setPin(pin);
		company.setPassword(password);
		company.setEmail(email);
		company.setName(name);
		company.setAddress(address);
		company.setDistrict(district);
		company.setLinkman(linkman);
		company.setMobile(mobile);
		company.setPhone(phone);
		company.setRepertory(repertory);
		company.setRepertoryAddress(repertoryAddress);
		return company;
	}
}
