package com.lingnan.usermansys.comm.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Md5Utils {
	/**
	 * MD5加密算法，将密码转换成定长16进制数
	 * 将byte数组转为16进制输出
	 * @param bytes
	 * @return
	 */
	public static String convertByteToHexString(byte[] bytes) {
		String result="";
		for(int i=0;i<bytes.length;i++) {
			int temp=bytes[i] & 0xff;
			String tempHex =Integer.toHexString(temp);
			if(tempHex.length()<2) {
				result +="0"+tempHex;
			}
			else {
				result +=tempHex;
			}
			
		}
		return result;
	}
	/**
	 * md5 加密
	 * @param message 需要加密的信息
	 * @return
	 */
	
	 
	public static String md5Jdk(String message) {
		String temp="";
		try {
			MessageDigest md5Digest=MessageDigest.getInstance("MD5");
			byte[] encodeMd5Digest=md5Digest.digest(message.getBytes());
			temp=convertByteToHexString(encodeMd5Digest);
			
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
}

