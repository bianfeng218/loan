package com.qp.loan.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qp.common.base.Message;
import com.qp.common.security.QpEncryptUtil;
import com.qp.loan.Contants.SystemConfig;
import com.qp.loan.domain.Administrator;
import com.qp.loan.manager.admin.AdministratorManager;
import com.qp.loan.service.AdministratorService;

/**
 * @author haiping
 *
 */
@Service
public class AdministratorServiceImpl implements AdministratorService{

	private @Resource AdministratorManager administratorManager;
	
	@Override
	public Message validateAdmin(String pin, String password) { 
		Administrator admin = administratorManager.findAdminByPin(pin);
		if(admin == null){
			return Message.failure("账号信息有误！");
		}
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("password", password);
		paramsMap.put("pin", admin.getPin());
		paramsMap.put("id", String.valueOf(admin.getId()));
		
		String sign = QpEncryptUtil.getSign(paramsMap, SystemConfig.authKey);
		if(!sign.equals(admin.getPassword())){
			return Message.failure("账号密码不匹配！");
		}
		
		return Message.success()
					.addModel("loginUser", admin);
	}
	
	public static void main(String[] args) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("password", "1qaz2wsx");
		paramsMap.put("pin", "admin");
		paramsMap.put("id", String.valueOf(1L));
		
		String sign = QpEncryptUtil.getSign(paramsMap, "!@#$LOAN)(*&");
		System.out.println(sign);
	}
}
