package com.qp.loan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qp.common.base.Message;
import com.qp.loan.domain.OverseasCompany;
import com.qp.loan.form.OverseasCompanyForm;
import com.qp.loan.service.OverseasCompanyService;

/**
 * @author haiping
 *
 */
@Controller
public class OverseasCompanyController {

	private @Resource OverseasCompanyService overseasCompanyService;
	
	private @ResponseBody Message create(OverseasCompanyForm form){
		Message msg = form.validate4Register();
		if(!msg.isSuccess()){
			return msg;
		}
		
		OverseasCompany company = (OverseasCompany)msg.getData().get("obj");
		return overseasCompanyService.create(company);
	}
	
}
