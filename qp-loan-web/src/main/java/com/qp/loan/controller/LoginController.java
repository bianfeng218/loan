package com.qp.loan.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qp.common.base.Message;
import com.qp.common.utils.QpCookieUtil;
import com.qp.loan.Contants.SystemConfig;
import com.qp.loan.domain.Administrator;
import com.qp.loan.form.LoginForm;
import com.qp.loan.service.OverseasCompanyService;
import com.qp.loan.service.SupplierService;
import static com.qp.common.contants.CookieContants.ID;
import static com.qp.common.contants.CookieContants.USER_PIN;
import static com.qp.common.contants.CookieContants.LOGIN_TIME;
import static com.qp.common.contants.CookieContants.CON_TYPE;
import static com.qp.common.contants.CookieContants.COOKIE_SAVE_TIME;
import static com.qp.common.contants.CookieContants.WEB_GBT;
import static com.qp.common.contants.CookieContants.WEB_ID;
import static com.qp.common.contants.CookieContants.WEB_LOGIN_TIME;
import static com.qp.common.contants.CookieContants.WEB_USER_PIN;

/**
 * @author haiping
 */
@Controller
@RequestMapping("/uc")
public class LoginController {

    private @Resource OverseasCompanyService overseasCompanyService;
    private @Resource SupplierService supplierService;

    @RequestMapping(value = "/login.do")
    public ModelAndView login() {
        return new ModelAndView("/login/login");
    }

    @RequestMapping(value = "/{type}/loginService.do", method = RequestMethod.POST)
    public
    @ResponseBody
    Message checkLogin(HttpServletResponse response, @RequestParam("type") String type,LoginForm form) throws IOException {

        Message msg = form.validate4Login();
        if (!msg.isSuccess()) {
            return msg;
        }

        if(type.equals("supplier")){
        	type="1";
        	msg = supplierService.validateLogin(form.getLoginName(), form.getPassword());
        }else{
        	type = "2";
        	msg = overseasCompanyService.validateLogin(form.getLoginName(), form.getPassword());
        }
       

        Administrator admin = (Administrator) msg.getData().get("loginUser");
        QpCookieUtil cookieUtils = new QpCookieUtil();

        cookieUtils.writeCookie(response, SystemConfig.domain,
                WEB_ID, String.valueOf(admin.getId()), COOKIE_SAVE_TIME);
        cookieUtils.writeCookie(response, SystemConfig.domain,
                WEB_USER_PIN, admin.getPin(), COOKIE_SAVE_TIME);
        cookieUtils.writeCookie(response, SystemConfig.domain,
                CON_TYPE, String.valueOf(admin.getType()), COOKIE_SAVE_TIME);
        cookieUtils.writeCookie(response, SystemConfig.domain,
                WEB_LOGIN_TIME, String.valueOf(System.currentTimeMillis()), COOKIE_SAVE_TIME);

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put(ID, String.valueOf(admin.getId()));
        paramsMap.put(USER_PIN, admin.getPin());
        paramsMap.put(LOGIN_TIME, String.valueOf(System.currentTimeMillis()));
        String sign = cookieUtils.getSign(paramsMap, SystemConfig.authKey);

        cookieUtils.writeCookie(response, SystemConfig.domain,
                WEB_GBT, sign, COOKIE_SAVE_TIME);

        return msg;
    }


}
