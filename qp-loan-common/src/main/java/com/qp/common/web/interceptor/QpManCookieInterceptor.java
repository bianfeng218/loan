package com.qp.common.web.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.qp.common.security.QpEncryptUtil;
import com.qp.common.utils.QpCookieUtil;
import com.qp.common.utils.QpTimeUtil;
import com.qp.common.web.auth.LoginContext;

import static com.qp.common.contants.CookieContants.*;


/**
 * DtApi后台cookie拦截器
 *
 * @author GaoXiang Date: 2015-3-4
 */
public class QpManCookieInterceptor extends HandlerInterceptorAdapter {

    private String secretKey;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Cookie工具类
     */
    private QpCookieUtil cookieUtil = new QpCookieUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id = cookieUtil.getCookieValue(request, SYS_ID);
        String userPin = cookieUtil.getCookieValue(request, SYS_USER_PIN);
        String loginTime = cookieUtil.getCookieValue(request, SYS_LOGIN_TIME);
        String type = cookieUtil.getCookieValue(request, CON_TYPE);

        /** 登陆成功后加密后的数据字符串 */
        String gbt = cookieUtil.getCookieValue(request, SYS_GBT);

        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put(ID, id);
        paramsMap.put(USER_PIN, userPin);
        paramsMap.put(LOGIN_TIME, loginTime);

        String sign = QpEncryptUtil.getSign(paramsMap, secretKey);
        int day = Integer.MAX_VALUE;

        QpTimeUtil timeUtil = QpTimeUtil.getInstance();
        Date loginDateTime = timeUtil.getDateFromString(loginTime, LOGIN_TIME_FOMART);

        if (loginDateTime != null) {
            day = timeUtil.intervalDays(loginDateTime, new Date());
        }

        // 加密字符串一致，且为三天之内输入密码登录成功
        if (sign.equals(gbt) && day <= COOKIE_SAVE_TIME) {
            // 登录用户
            request.setAttribute(LOGIN_STATUS, true);
            LoginContext context = new LoginContext();
            context.setId(Integer.valueOf(id));
            context.setPin(userPin);
            context.setType(Short.valueOf(type));
            LoginContext.setLoginContext(context);
        } else {
            // 未登录
            request.setAttribute(LOGIN_STATUS, false);
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (LoginContext.getLoginContext() != null) {
            String pin = LoginContext.getLoginContext().getPin();
            modelAndView.addObject("pin", pin);
        }
    }
}
