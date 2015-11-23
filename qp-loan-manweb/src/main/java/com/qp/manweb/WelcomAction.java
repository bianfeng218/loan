package com.qp.manweb;

import javax.servlet.http.HttpServletRequest;

import com.qp.loan.service.auth.AuthCode;
import com.qp.loan.service.auth.Privilege;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页欢迎页面
 * 
 * @author GaoXiang Date: 2015-4-7
 */
@Controller
@Scope("prototype")
public class WelcomAction {

    @Privilege(AuthCode.LOGIN)
    @RequestMapping("index.do")
    public String index(Model model, HttpServletRequest request) {
        return "index";
    }
    
    @RequestMapping("welcome.do")
    public String welcome(Model model) {
        return "welcome";
    }
    
}
