package com.qp.loan.service.auth;

import com.qp.common.web.auth.LoginContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 系统权限拦截器
 * Created by yfliqiang on 2015/9/16.
 */
public class PrivilegeInterceptor implements HandlerInterceptor {
    private final static Log log = LogFactory.getLog(PrivilegeInterceptor.class);
    private boolean enabled = true; // 默认为启用
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //禁用后直接返回
        if (!enabled) return true;
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (!method.isAnnotationPresent(Privilege.class)) {
            return true;
        }
        Privilege privilegeAnnotation = method.getAnnotation(Privilege.class);
        //执行此方法需要的权限码
        String[] privileges = privilegeAnnotation.value();

        String pin = LoginContext.getLoginContext().getPin();;//获取当前登录用户名
        if(!authService.hasPrivilege(pin, privileges)){
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                response.setStatus(401);
            } else {
                response.sendRedirect("error");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
