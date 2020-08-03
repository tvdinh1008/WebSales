package com.tvdinh.utils;

import javax.servlet.http.HttpServletRequest;

/*
 * User: id,username,fullname
 */

public class SessionUtil {
	private static SessionUtil sessionUtil=null;
	/*
	 * Khởi tạo session nếu chưa
	 */
	public static SessionUtil getInstance()
	{
		if(sessionUtil==null)
		{
			sessionUtil=new SessionUtil();
		}
		return sessionUtil;
	}
	
	
	/*
	 * Duy trì thông tin người dùng
	 * Lấy session đã tạo request.getSession() sau đó put giá trị vào
	 */
	public void putValue(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
	
	/*
	 * Lấy thông tin ra: từ Object ta sẽ ép thành kiểu dữ liệu ta mong muốn
	 */
	public Object getValue(HttpServletRequest request, String key)
	{
		return request.getSession().getAttribute(key);
	}
	
	/*
	 * Thoát ra khỏi hệ thống
	 */
	public void removeValue(HttpServletRequest request,String key) {
		request.getSession().removeAttribute(key);
	}
	
}
