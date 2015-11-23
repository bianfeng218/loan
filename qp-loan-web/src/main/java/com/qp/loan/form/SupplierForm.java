package com.qp.loan.form;

import com.qp.common.base.Message;
import com.qp.common.utils.QpStringUtil;
import com.qp.loan.domain.Supplier;

/**
 * @author haiping
 *
 */
public class SupplierForm {
	private String pin;
	private String password;
	private String confirmPassword;
	private String email;
	private String name;
	private String address;
	private String district;
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
		
		Supplier supplier = convertForm4Register();
		return Message.success()
				.addModel("obj", supplier);
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
		if(QpStringUtil.isEmpty(linkman)){
			return Message.failure("联系人为空！");
		}
		if(QpStringUtil.isEmpty(mobile)){
			return Message.failure("手机为空！");
		}
		if(QpStringUtil.isEmpty(phone)){
			return Message.failure("固定电话为空！");
		}
		
		Supplier supplier = convertForm4Enroll();
		return Message.success()
				.addModel("obj", supplier);
	}
	
	private Supplier convertForm4Register(){
		Supplier supplier = new Supplier();
		supplier.setPin(pin);
		supplier.setPassword(password);
		return supplier;
	}
	private Supplier convertForm4Enroll(){
		Supplier supplier = new Supplier();
		supplier.setPin(pin);
		supplier.setPassword(password);
		supplier.setEmail(email);
		supplier.setName(name);
		supplier.setAddress(address);
		supplier.setDistrict(district);
		supplier.setLinkman(linkman);
		supplier.setMobile(mobile);
		supplier.setPhone(phone);
		return supplier;
	}
}
