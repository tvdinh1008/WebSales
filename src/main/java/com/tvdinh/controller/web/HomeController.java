package com.tvdinh.controller.web;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tvdinh.dao.impl.CustomerDAO;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.utils.FormUtil;
import com.tvdinh.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/dang-ky", "/quen-mat-khau", "/thoat" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -2921692510702300868L;
	
	ResourceBundle resourceBundle=ResourceBundle.getBundle("message");
	
	
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

			CustomerDAO cus = new CustomerDAO();
			List<CustomerModel> list = cus.findAll();
			request.setAttribute("listCus", list);

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
			CustomerModel cus = null;
			cus = FormUtil.tModel(CustomerModel.class, request);

			// check xem có đúng ko
			cus.setName("Trần Văn Định");
			cus.setId(1l);
			cus.setRoleid(2l);
			cus.getRole().setCode("USER");

			if (cus != null) {// getSession bắt buộc phải có vì nó request tới trang khác(khác với forward)
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
