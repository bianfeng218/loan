package com.qp.loan.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ResponseBody;

import com.qp.common.base.Message;
import com.qp.common.web.auth.LoginContext;
import com.qp.loan.domain.Supplier;
import com.qp.loan.form.SupplierForm;
import com.qp.loan.service.SupplierService;

/**
 * @author haiping
 *
 */
public class SupplierController {
	
	private @Resource SupplierService supplierService;

	private @ResponseBody Message create(SupplierForm form){
		Message msg = form.validate4Register();
		if(!msg.isSuccess()){
			return msg;
		}
		
		Supplier supplier = (Supplier)msg.getData().get("obj");
		return supplierService.create(supplier);
	}
	
	public @ResponseBody Message enroll(SupplierForm form){
		Message msg = form.validate4Enroll();
		if(!msg.isSuccess()){
			return msg;
		}
		
		Supplier supplier = (Supplier)msg.getData().get("obj");
		LoginContext loginContext = LoginContext.getLoginContext();
		supplier.setId(loginContext.getId());
		
		return supplierService.enroll(supplier);
	}
	
	
}
