package com.ruicai.diary.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
    /**
     * digest:消化，吸收
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
	public static String EncoderPwdByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//指定采用MD5算法加密
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en=new BASE64Encoder();
		return base64en.encode(md5.digest(str.getBytes("utf-8")));
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(EncoderPwdByMd5("jack"));
	}
}
