package com.lingnan.usermansys.comm.util;
/**
 * / 判断是否为空
 * @author 123
 *
 */
public class TextUtils {
	
	public static boolean isEmpty(CharSequence str) {
		if (str == null || str.length() == 0)
			return true;
		else
			return false;

	}

	

}
