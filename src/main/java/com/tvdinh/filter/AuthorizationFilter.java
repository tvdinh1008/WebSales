package com.tvdinh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tvdinh.model.CustomerModel;
import com.tvdinh.utils.SessionUtil;

public class AuthorizationFilter implements Filter{

	@SuppressWarnings("unused")
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context=filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		
		/*
		 * bình thường url sẽ có dạng: http://localhost:8080/WebSales/admin-home
		 * Để bỏ cái /WebSales để request.getRequestURI() lấy đc cái admin-home
		 * Vào Servers->Tomcat->server.xml tìm đến <Context docBase="WebSales" path="/" reloadable="true" source="WebSales"/>
		 * rồi chỉnh lại chỗ path
		 * url bây giờ có dạng: http://localhost:8080/trang-chu
		 */
		
		String url=request.getRequestURI();//lấy đc link vd /trang-chu ,/admin-home,....
		
		if(url.startsWith("/admin"))
		{
			CustomerModel cus=(CustomerModel) SessionUtil.getInstance().getValue(request, "CUSTOMERMODEL");
			if(cus!=null)
			{
				if(cus.getRole().getCode().equals("ADMIN"))
				{
					filterChain.doFilter(servletRequest, servletResponse);
				}
				else if(cus.getRole().getCode().equals("USER"))
				{
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
				
			}else
			{
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
		}
		else
		{
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
	}
	@Override
	public void destroy() {
		
	}

}
