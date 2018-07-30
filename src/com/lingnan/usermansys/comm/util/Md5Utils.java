package com.lingnan.usermansys.comm.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Md5Utils {
	/*
	 * 将byte数组转为16进制输出
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
	/*
	 * md5 加密
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

