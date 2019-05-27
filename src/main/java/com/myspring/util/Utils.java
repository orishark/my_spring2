package com.myspring.util;

public class Utils {

	public static String replaceTagToString(String content) {
		
		if(null != content) {
			
			content = content.replace("<", "&lt;"); 
			content = content.replace(">", "&gt;");
		}
		return content;
	}
}
