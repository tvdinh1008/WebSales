package com.tvdinh.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tvdinh.model.CustomerModel;
import com.tvdinh.service.ICustomerService;
import com.tvdinh.utils.FormUtil;
import com.tvdinh.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/dang-ky", "/quen-mat-khau", "/thoat" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -2921692510702300868L;
	
	ResourceBundle resourceBundle=ResourceBundle.getBundle("message");
	
	@Inject
	private ICustomerService customerService;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			// url=/dang-nhap?action=login
			
			String message=request.getParameter("message");
			String alert=request.getParameter("alert");
			if(message!=null && alert !=null)
			{
				request.setAttribute("message",resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("register")) {
			// url=/dang-ky?action=register
			RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("forgot-password")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/forgot-password.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			// url=/thoat?action=logout
			SessionUtil.getInstance().removeValue(request, "CUSTOMERMODEL");
			// chuyển hướng tới controller , khác với cái RequestDispatcher
			response.sendRedirect(request.getContextPath() + "/trang-chu");

		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			CustomerModel cus =FormUtil.tModel(CustomerModel.class, request);
			cus=customerService.findByUserNameandPasswordAndStatus(cus.getUsername(), cus.getPassword(), 1);
//			cus.getRole().setCode("ADMIN");
//			cus.getRole().setName("ADMIN");
			if (cus != null) {
				// getSession bắt buộc phải có vì nó request tới trang khác(khác với forward)
				// request.getSession().setAttribute("CUSTOMERMODEL", cus);
				SessionUtil.getInstance().putValue(request, "CUSTOMERMODEL", cus);
				if (cus.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath() + "/trang-chu");

				}else if (cus.getRole().getCode().equals("ADMIN"))
				{
					response.sendRedirect(request.getContextPath() + "/admin-home");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}

	}

}
