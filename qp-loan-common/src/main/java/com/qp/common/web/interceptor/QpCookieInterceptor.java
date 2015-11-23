package com.qp.common.web.interceptor;

import static com.qp.common.contants.CookieContants.CON_TYPE;
import static com.qp.common.contants.CookieContants.USER_ID;
import static com.qp.common.contants.CookieContants.USER_ACCOUNT;
import static com.qp.common.contants.CookieContants.LOGIN_TIME;
import static com.qp.common.contants.CookieContants.BU2X_STRING;
import static com.qp.common.contants.CookieContants.LOGIN_STATUS;
import static com.qp.common.contants.CookieContants.LOGIN_TIME_FOMART;
import static com.qp.common.contants.CookieContants.COOKIE_SAVE_TIME;








import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qp.common.security.QpEncryptUtil;
import com.qp.common.utils.QpCookieUtil;
import com.qp.common.utils.QpTimeUtil;
import com.qp.common.web.auth.LoginContext;



/**
 * cookie验证拦截器
 * 
 * @author GaoXiang Date: 2015-5-6
 */
public class QpCookieInterceptor extends HandlerInterceptorAdapter {
    
    private QpCookieUtil cookieUtil = new QpCookieUtil();
    
    /**
     * 登陆密钥
     */
    private String secretKey;
    
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = cookieUtil.getCookieValue(request, USER_ID);
        String userAccount = cookieUtil.getCookieValue(request, USER_ACCOUNT);
        String loginTime = cookieUtil.getCookieValue(request, LOGIN_TIME);
        String type = cookieUtil.getCookieValue(request, CON_TYPE);
        
        /** 登陆成功后加密后的数据字符串 */
        String bu2xString = cookieUtil.getCookieValue(request, BU2X_STRING);
        
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put(USER_ID, userId);
        paramsMap.put(USER_ACCOUNT, userAccount);
        paramsMap.put(LOGIN_TIME, loginTime);
        
        String sign = QpEncryptUtil.getSign(paramsMap, secretKey);
        
        int day = 0;
        
        QpTimeUtil timeUtil = QpTimeUtil.getInstance();
        Date loginDateTime = timeUtil.getDateFromString(loginTime, LOGIN_TIME_FOMART);
        
        if (loginDateTime != null) {
            day = timeUtil.intervalDays(loginDateTime, new Date());
        }
        
        // 加密字符串一致，且为三天之内输入密码登录成功
        if (sign.equals(bu2xString) && day <= COOKIE_SAVE_TIME) {
            // 登录用户
            request.setAttribute(LOGIN_STATUS, true);
            LoginContext context = new LoginContext();
            context.setId(Integer.valueOf(userId));
            context.setPin(userAccount);
            context.setType(Short.valueOf(type));
            LoginContext.setLoginContext(context);
        }
        else {
            request.setAttribute(LOGIN_STATUS, false);
        }
        
        return super.preHandle(request, response, handler);
    }
    
}
