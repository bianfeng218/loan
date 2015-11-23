package com.qp.common.dao;


/**
 * @author huanghaiping
 *
 */
public class DaoUtils {
	/**
	 * 对给定的数字进行取模运算
	 * @param dividend 除数
	 * @param divisor 被除数
	 * @return 余数
	 */
	public static int getMod(double dividend , int divisor){
		
		return (int)(dividend%divisor);
	}
}
