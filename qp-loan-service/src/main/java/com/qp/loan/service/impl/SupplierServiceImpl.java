package com.qp.loan.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.qp.common.base.Message;
import com.qp.common.security.QpEncryptUtil;
import com.qp.loan.Contants.SystemConfig;
import com.qp.loan.dao.SupplierDao;
import com.qp.loan.domain.Supplier;
import com.qp.loan.manager.admin.SupplierManager;
import com.qp.loan.service.SupplierService;

/**
 * @author haiping
 *
 */
@Service
public class SupplierServiceImpl implements SupplierService{
	
	private static final Log log = LogFactory.getLog(SupplierServiceImpl.class);
	
	private @Resource SupplierDao supplierDao;
	private @Resource SupplierManager supplierManager;
	
	@Override
	public Message validateLogin(String pin, String password) {
		
		Supplier supplier = supplierManager.findSupplierByPin(pin);
		if(supplier==null){
			return Message.failure("不存在此用户！");
		}
		if(!supplier.getPassword().equals(QpEncryptUtil.encoderByDES(supplier.getPassword(), SystemConfig.authKey))){
			return Message.failure("用户密码错误！");
		}
		return Message.success();
	}

	@Override
	public Message create(Supplier supplier) {
		supplier.setPassword(QpEncryptUtil.encoderByDES(supplier.getPassword(), SystemConfig.authKey));
		Integer id = null;
		try {
			id = supplierDao.add(supplier);
		} catch (Exception e) {
			log.error("db error!", e);
		}
		
		if(id==null||id<1){
			return Message.failure("注册失败！");
		}
		return Message.success();
	}
	
	@Override
	public Message enroll(Supplier supplier) {
		
		int count = 0;
		
		try {
			count = supplierManager.update(supplier);
		} catch (Exception e) {
			log.error("db error!", e);
		}
		
		if(count<1){
			return Message.failure("报名失败！");
		}		
		
		return Message.success();
	}
}
