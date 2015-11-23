package com.qp.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * 
 * @author gaoxiang 2013-7-16
 */
public class QpStringUtil {
    
    public static final int PARSE_INT_FAILED = -1;// 当用字符串转化int型失败时返回该值
    
    public static final Long PARSE_LONG_FAILED = -1l;// 当用字符串转化long型失败时返回该值
    
    public static final double PARSE_DOUBLE_FAILED = -1.0;// 当用字符串转化double型失败时返回该值
    
    /**
     * 判断字符串是否为null或空串
     * 
     * @param value
     * @return true 空串
     */
    public static boolean isEmpty(String value) {
        return null == value || value.trim().length() == 0;
    }
    
    /**
     * 判断字符串是否为null或空串
     * 
     * @param value
     * @return true 不是空串
     */
    public static boolean isNotEmpty(String value) {
        return null != value && value.trim().length() > 0;
    }
    
    /**
     * 使用分号将字符串划分为数组
     * 
     * @param s 字符串
     * @param sign 分隔符
     * @return 若参数为null则返回一个长度为0的String数组(避免出现NullPointerException)，否则返回使用分号分隔后的数组
     */
    public static String[] splitString(String s, String sign) {
        return (null == s) ? new String[] {} : s.split(sign);
    }
    
    /**
     * 将s转化为int型数字，如果转化失败，不会抛出RunTimeException，而是返回PARSE_INT_FAILED(-1)
     * 
     * @param s
     * @return
     */
    public static int parseInt(String s) {
        try {
            int result = Integer.parseInt(s);
            return result;
        }
        catch (Exception e) {
            return PARSE_INT_FAILED;
        }
    }
    
    /**
     * 将s转化为int数组(所有数字必须大于0)，如果某个数字转化失败，会跳过该数字，继续转化，将所有转化成功的数字放入List中
     * 
     * @param s 字符串
     * @param sign 分隔符
     * @return
     */
    public static List<Integer> parseIntList(String s, String sign) {
        List<Integer> result = new ArrayList<Integer>();
        if (isNotEmpty(s)) {
            String[] intList = s.split(sign);
            for (String intString : intList) {
                int i = parseInt(intString);
                if (i >= 0) {
                    result.add(i);
                }
            }
        }
        
        return result;
    }
    
    /**
     * 将s转化为Integer对象，如果转化失败，不会抛出RunTimeException，而是返回null
     * 
     * @param s
     * @return
     */
    public static Integer parseInteger(String s) {
        try {
            int result = Integer.parseInt(s);
            return new Integer(result);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 将s转化为long型数字，如果转化失败，不会抛出RunTimeException，而是返回PARSE_LONG_FAILED(-1)
     * 
     * @param s
     * @return
     */
    public static long parseLong(String s) {
        try {
            long result = Long.parseLong(s);
            return result;
        }
        catch (Exception e) {
            return PARSE_LONG_FAILED;
        }
    }
    
    /**
     * 将s转化为long数组(所有数字必须大于0)，如果某个数字转化失败，会跳过该数字，继续转化，将所有转化成功的数字放入List中
     * 
     * @param s 字符串
     * @param sign 分隔符
     * @return
     */
    public static List<Long> parseLongList(String s, String sign) {
        List<Long> result = new ArrayList<Long>();
        if (isNotEmpty(s)) {
            String[] longList = s.split(sign);
            for (String longString : longList) {
                long i = parseLong(longString);
                if (i >= 0) {
                    result.add(i);
                }
            }
        }
        
        return result;
    }
    
    /**
     * 将s转化为double型数字，如果转化失败，不会抛出RunTimeException，而是返回PARSE_DOUBLE_FAILED(-1.0)
     * 
     * @param s
     * @return
     */
    public static double parseDouble(String s) {
        try {
            double result = Double.parseDouble(s);
            return result;
        }
        catch (Exception e) {
            return PARSE_DOUBLE_FAILED;
        }
    }
    
    /**
     * 判断是否所有字符串都非空
     * 
     * @param value
     * @return 如果所有字符串都非空，返回true,否则返回false
     */
    public static boolean isAllStringNotBlank(String... values) {
        for (String s : values) {
            if (isEmpty(s)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 调用Object的toString方法，如果参数为空，返回null
     * 
     * @param o
     * @return
     */
    public static String getObjectString(Object o) {
        if (null == o) {
            return null;
        }
        
        return o.toString();
    }
    
    /**
     * 输入参数过滤
     * 
     * @param param 需要处理过滤的字符串
     * @param regex 需过滤的字符正则表达式<br>
     * 不使用该参数是默认：[\\[\\]<>!@$%.&*(:?)=_+]<br>
     * 中括号包含表示其中任意一个，\\为正则表达式中的\
     * @return 过滤后的新字符串
     * @author guobing
     * @since 2014-9-18
     */
    public static String paramFilter(String param, String... regex) {
        if (isEmpty(param)) {
            return param;
        }
        String reg = regex.length > 0 ? regex[0] : "[\\[\\]<>!@$%.&*(:?)=_+]";
        
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(param);
        param = matcher.replaceAll("");
        
        return param;
    }
    
    /**
     * 判断两个字符串是否相同(比较trim后的结果，前后有空格不算)
     * 
     * @param s1
     * @param s2
     * @return true相同 false不相同
     */
    public static boolean isEqual(String s1, String s2) {
        // 两个字符串都为空，则相同
        if (isEmpty(s1) && isEmpty(s2)) {
            return true;
        }
        
        // 两个字符串都非空，则比较trim后的结果
        if (isNotEmpty(s1) && isNotEmpty(s2)) {
            return s1.trim().equals(s2.trim());
        }
        
        // 一个为空，一个非空，则不同
        return false;
    }
    
    /**
     * 判断两个字符串是否相同(不区分大小写，比较trim后的结果，前后有空格不算)
     * 
     * @param s1
     * @param s2
     * @return true相同 false不相同
     */
    public static boolean isEqualIgnoreCase(String s1, String s2) {
        // 两个字符串都为空，则相同
        if (isEmpty(s1) && isEmpty(s2)) {
            return true;
        }
        
        // 两个字符串都非空，则比较trim后的结果
        if (isNotEmpty(s1) && isNotEmpty(s2)) {
            return s1.trim().equalsIgnoreCase(s2.trim());
        }
        
        // 一个为空，一个非空，则不同
        return false;
    }
    
    /**
     * 将bean类转化为String对象(在bean对象的toString方法中调用)
     * 
     * @param bean
     * @return
     */
    public static String beanObject2String(Object bean) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            sb.append(field.getName());
            sb.append("={");
            try {
                field.setAccessible(true);// 设置可以访问私有成员变量值
                if (field.get(bean) instanceof List) {
                    @SuppressWarnings("unchecked")
                    List<Object> list = (List<Object>) field.get(bean);
                    for (Object o : list) {
                        sb.append("[" + o.toString() + "]");
                    }
                }
                else if (field.get(bean) instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<Object, Object> map = (Map<Object, Object>) field.get(bean);
                    for (Entry<Object, Object> entry : map.entrySet()) {
                        sb.append("[key=" + entry.getKey().toString() + ",value=" + entry.getValue().toString() + "]");
                    }
                }
                else {
                    sb.append(field.get(bean));
                }
            }
            catch (Exception e) {
            }
            sb.append("};");
        }
        
        // 去掉末尾的空格
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    /**
     * <p>
     * Removes a substring only if it is at the end of a source string, otherwise returns the source string.
     * </p>
     * <p>
     * A <code>null</code> source string will return <code>null</code>. An empty ("") source string will return the empty string. A <code>null</code>
     * search string will return the source string.
     * </p>
     * 
     * <pre>
     * StringUtils.removeEnd(null, *)      = null
     * StringUtils.removeEnd("", *)        = ""
     * StringUtils.removeEnd(*, null)      = *
     * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeEnd("abc", "")    = "abc"
     * </pre>
     * 
     * @param str the source String to search, may be null
     * @param remove the String to search for and remove, may be null
     * @return the substring with the string removed if found, <code>null</code> if null String input
     * @since 2.1
     */
    public static String removeEnd(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }
    
    /**
     * 判断两个Integer是否相同
     * 
     * @param i1
     * @param i2
     * @return
     */
    public static boolean isIntegerEqual(Integer i1, Integer i2) {
        if (i1 == null && i2 == null) {
            return true;
        }
        
        if (i1 != null && i2 != null) {
            return i1.intValue() == i2.intValue();
        }
        
        return false;
    }
}
