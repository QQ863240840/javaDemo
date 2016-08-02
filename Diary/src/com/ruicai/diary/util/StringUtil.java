package com.ruicai.diary.util;

public class StringUtil {
    /**
     * 创建工具类的时候可以充分考虑一点，使用静态方法
     * 这样就可以直接使用，不用创建对象。
     * 
     * @param str
     * @return
     */
	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	
}
