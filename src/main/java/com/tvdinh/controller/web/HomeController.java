package com.tvdinh.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/dang-ky","/quen-mat-khau"})
public class HomeController extends HttpServlet{
	
	
	private static final long serialVersionUID = -2921692510702300868L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String action=request.getParameter("action");
        if(action!=null && action.equals("login"))
        {
        	// url=/dang-nhap?action=login
        	RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
            rd.forward(request, response);
        }
        else if(action!=null && action.equals("register"))
        {
        	// url=/dang-ky?action=register
        	RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
            rd.forward(request, response);
        }
        else if(action!=null && action.equals("forgot-password"))
        {
        	RequestDispatcher rd = request.getRequestDispatcher("/views/forgot-password.jsp");
            rd.forward(request, response);
        }
        else if(action!=null && action.equals("login"))
        {
        	
        }
        else
        {
        	RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        }
        
        
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
