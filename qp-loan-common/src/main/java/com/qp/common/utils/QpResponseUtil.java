package com.qp.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * 与响应相关的工具类
 * 
 * @author guobing Date:2014-7-15
 */
public final class QpResponseUtil {
    
    private QpResponseUtil() {
    }
    
    /**
     * 返回字符串html响应
     * 
     * @param response
     * @param obj
     */
    public static void responseHtml(HttpServletResponse response, Object obj) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(obj.toString());
            response.getWriter().flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 以文本响应返回
     * 
     * @param str
     * @throws Exception
     */
    public static void responseString(HttpServletResponse response, String str) {
        
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(str);
            response.getWriter().flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 以json字符串响应返回
     * 
     * @param str
     * @throws Exception
     */
    public static void responseJson(HttpServletResponse response, String str) {
        
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(str);
            response.getWriter().flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 以json字符串响应返回
     * 
     * @param obj
     * @throws Exception
     */
    public static void responseJson(HttpServletResponse response, Object obj) {
        
        responseJson(response, JSON.toJSONString(obj));
    }
    
    /**
     * 以jsonp字符串响应返回
     * 
     * @param obj
     * @param jsonCallback
     * @throws Exception
     */
    public static void responseJsonp(HttpServletResponse response, Object obj, String jsonCallback) {
        String jsonString = JSON.toJSONString(obj);
        responseJsonp(response, jsonString, jsonCallback);
    }
    
    /**
     * 以jsonp字符串响应返回
     * 
     * @param obj
     * @param jsonCallback
     * @throws Exception
     */
    public static void responseJsonp(HttpServletResponse response, String jsonString, String jsonCallback) {
        
        if (QpStringUtil.isNotEmpty(jsonCallback)) {
            jsonString = jsonCallback + "(" + jsonString + ")";
        }
        responseJson(response, jsonString);
    }
    
}
