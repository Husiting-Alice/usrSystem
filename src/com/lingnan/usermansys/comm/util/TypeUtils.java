package com.lingnan.usermansys.comm.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.lingnan.usermansys.comm.exception.DateException;
import com.lingnan.usermansys.comm.exception.EmailException;
public class TypeUtils {
	/**
	 * 进行邮箱验证的正则表达式
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+"
			+ "[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 *  字符串转日期
	 * @param str 需要转换为日期的字符串，格式为：yyyy-MM-dd
	 * @return Date类型数据
	 */
	public static Date strToDate(String str) {
		Date date = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 将字符串解析成Date类型  
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sd.parse(str);
		} catch (Exception e) {
//			System.out.println("字符串转日期失败！");
			throw new DateException("输入的字符串格式错误",e);
		}
		return date;

	}

	/**
	 * 日期转字符串
	 * @param dt 需要转换为字符串的日期
	 * @return String类型数据
	 */
	public static String dateToStr(Date dt) {
		String str = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 将Date类型解析成字符串
			str = sd.format(dt);

		} catch (Exception e) {
//			System.out.println("日期转字符串失败！");
			throw new DateException("输入的日期格式错误",e);
		}
		return str;

	}

	/**
	 *  邮箱格式验证
	 * @param e_mail 待验证的邮件地址
	 * @return 返回布尔类型
	 */
	public static boolean checkEmail(String e_mail) {
		boolean flag = false;
		try {
		flag = Pattern.matches(REGEX_EMAIL, e_mail);
		}catch (Exception e) {
		   throw new EmailException("邮箱地址格式不正确",e);
		}
		return flag;
	}

}
