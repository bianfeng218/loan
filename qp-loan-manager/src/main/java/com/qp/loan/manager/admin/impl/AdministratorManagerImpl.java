package com.qp.loan.manager.admin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.qp.loan.dao.AdministratorDao;
import com.qp.loan.domain.Administrator;
import com.qp.loan.manager.admin.AdministratorManager;

/**
 * @author haiping
 *
 */
@Component
public class AdministratorManagerImpl implements AdministratorManager{

	private @Resource AdministratorDao administratorDao;
	
	@Override
	public Administrator findAdminByPin(String pin) {
		Administrator query = new Administrator();
		query.setPin(pin);
		List<Administrator> admins = administratorDao.queryByCondition(query);
		if(CollectionUtils.isNotEmpty(admins)){
			return admins.get(0);
		}
		
		return null;
	}

}
