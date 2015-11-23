package com.qp.loan.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.qp.common.base.Message;
import com.qp.common.security.QpEncryptUtil;
import com.qp.loan.Contants.SystemConfig;
import com.qp.loan.dao.OverseasCompanyDao;
import com.qp.loan.domain.OverseasCompany;
import com.qp.loan.manager.admin.OverseasCompanyManager;
import com.qp.loan.service.OverseasCompanyService;

/**
 * @author haiping
 * 
 */
@Service
public class OverseasCompanyServiceImpl implements OverseasCompanyService {
	
	private static final Log log = LogFactory.getLog(OverseasCompanyServiceImpl.class);
	
	private @Resource OverseasCompanyDao overseasCompanyDao;
	private @Resource OverseasCompanyManager overseasCompanyManager;
	
	@Override
	public Message validateLogin(String pin, String password) {
		
		OverseasCompany company = overseasCompanyManager.findOverseasCompanyByPin(pin);
		if(company==null){
			return Message.failure("不存在此用户！");
		}
		if(!company.getPassword().equals(QpEncryptUtil.encoderByDES(password, SystemConfig.authKey))){
			return Message.failure("用户密码错误！");
		}
		return Message.success();
	}
	
	@Override
	public Message create(OverseasCompany company) {

		company.setPassword(QpEncryptUtil.encoderByDES(company.getPassword(), SystemConfig.authKey));
		
		Integer id = null;
		try {
			id = overseasCompanyDao.add(company);
		} catch (Exception e) {
			log.error("db error", e);
		}
		
		if(id==null||id<1){
			return Message.failure("注册失败！");
		}
		
		return Message.success();
	}
	
	@Override
	public Message enroll(OverseasCompany company) {
		
		int count = 0;
		
		try {
			count = overseasCompanyManager.update(company);
		} catch (Exception e) {
			log.error("db error!", e);
		}
		
		if(count<1){
			return Message.failure("报名失败！");
		}		
		
		return Message.success();
	}
}
