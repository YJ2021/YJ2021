package cn.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;

public class AJAX_JSON {
   public static void returnJson(HttpServletResponse response,Object msg) {
	   try {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		out.print(msg);
		System.out.println("JSON返回到界面...");
		out.flush(); out.close();
	} catch (IOException e) {
		e.printStackTrace();
	} 
   }
   /***
	 * 过滤属性
	 * */
	public static PropertyFilter filterProperts(final String...propNames){
		PropertyFilter propertyFilter=new PropertyFilter() {
			
			public boolean apply(Object arg0, String propertyName, Object arg2) {
				for (String pname : propNames) {
					if(propertyName.equals(pname)){
						return false;//过滤
					}
				}
				return true;
			}
		};
		
		return propertyFilter;
	}
	   
}
