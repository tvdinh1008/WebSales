package com.tvdinh.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin-product"})
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = -2992969001520137967L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/list.jsp");
        rd.forward(request, response);
		
	}
	
}
