package com.qp.common.utils;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.qp.common.security.QpEncryptUtil;
import com.qp.common.web.cookie.QpCookie;

/**
 * @author haiping
 *
 */
public class QpCookieUtil
{
	
  private Map<String, QpCookie> cookieMap;

  public String getCookieValue(HttpServletRequest servletRequest, String name)
  {
    Cookie[] cookies = servletRequest.getCookies();
    if ((cookies != null) && (cookies.length > 0)) {
      for (Cookie cookie : cookies) {
        String cookieName = cookie.getName();
        if (cookieName.equals(name)) {
          if ((this.cookieMap != null) && (this.cookieMap.containsKey(name))) {
            QpCookie dlCookie = (QpCookie)this.cookieMap.get(name);
            return dlCookie.getValue(cookie.getValue());
          }
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  public void deleteCookie(HttpServletResponse servletResponse, String name)
  {
    Cookie cookie;
    if ((this.cookieMap != null) && (this.cookieMap.containsKey(name))) {
      QpCookie dlCookie = (QpCookie)this.cookieMap.get(name);
      cookie = dlCookie.newCookie(null);
    } else {
      cookie = new Cookie(name, null);
    }
    cookie.setMaxAge(0);
    servletResponse.addCookie(cookie);
  }

  public void setCookie(HttpServletResponse servletResponse, String name, String value)
  {
    if ((this.cookieMap != null) && (this.cookieMap.containsKey(name))) {
      QpCookie dlCookie = (QpCookie)this.cookieMap.get(name);
      Cookie cookie = dlCookie.newCookie(value);
      servletResponse.addCookie(cookie);
    } else {
      throw new RuntimeException("Cookie " + name + " is undefined!");
    }
  }

public void setDlCookie(List<QpCookie> dlCookieList)
  {
    if (dlCookieList != null) {
      HashMap<String,QpCookie> dlCookieHashMap = new HashMap<String,QpCookie>(dlCookieList.size());
      for (QpCookie dlCookie : dlCookieList) {
        dlCookieHashMap.put(dlCookie.getName(), dlCookie);
      }
      this.cookieMap = dlCookieHashMap;
    }
  }

  public void invalidate(HttpServletRequest request, HttpServletResponse response)
  {
    if ((this.cookieMap != null) && (this.cookieMap.size() > 0))
      for (@SuppressWarnings("rawtypes") Map.Entry entry : this.cookieMap.entrySet()) {
        String key = (String)entry.getKey();
        QpCookie dlCookie = (QpCookie)entry.getValue();
        if ((dlCookie.getExpiry() < 1) && 
          (StringUtils.isNotEmpty(getCookieValue(request, key))))
          deleteCookie(response, key);
      }
  }
  
  /**
   * 写cookie
   * 
   * @param response
   * @param domain
   * @param name
   * @param value
   * @param days
   * @throws ServletException
   * @throws IOException
   */
  public void writeCookie(HttpServletResponse response, String domain, String name, String value, int seconds) {
      value = QpEncryptUtil.urlEncode(value);
      Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(seconds);
      cookie.setPath("/");
      cookie.setDomain(domain);
      response.addCookie(cookie);
  }
  
  /**
   * 生成签名
   * 
   * @param paramsMap
   * @return
   */
  public String getSign(Map<String, String> paramsMap, String secretKey) {
      Set<String> keySet = paramsMap.keySet();
      String[] keys = keySet.toArray(new String[keySet.size()]);
      
      Arrays.sort(keys);
      
      StringBuffer sb = new StringBuffer();
      for (String key : keys) {
          sb.append(key);
          sb.append(paramsMap.get(key));
      }
      sb.append(secretKey);
      return QpEncryptUtil.MD5HexString(sb.toString());
  }
}