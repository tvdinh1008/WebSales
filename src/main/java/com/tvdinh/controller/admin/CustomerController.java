package com.tvdinh.controller.admin;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tvdinh.model.CustomerModel;
import com.tvdinh.paging.PageRequest;
import com.tvdinh.paging.Pageble;
import com.tvdinh.service.ICustomerService;
import com.tvdinh.sort.Sorter;
import com.tvdinh.utils.FormUtil;


@WebServlet(urlPatterns = {"/admin-customer"})
public class CustomerController extends HttpServlet{

	private static final long serialVersionUID = -2225215970552386072L;
	
	@Inject
	private ICustomerService customerService;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        
        CustomerModel model=FormUtil.tModel(CustomerModel.class, request);
       /*
        CustomerModel model=new CustomerModel();
        
        String pageStr=request.getParameter("page");
        String maxPageItemStr=request.getParameter("maxPageItem");
        if(pageStr!=null)
        {
        	model.setPage(Integer.parseInt(pageStr));
        }
        else
        {
        	model.setPage(1);
        }
        if(maxPageItemStr!=null)
        {
        	model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
        }
        else
        {
        	model.setMaxPageItem(2);
        }
       	*/
        
        //Integer offset=(model.getPage()-1)*model.getMaxPageItem();//code logic->ko để trong controller
        Pageble pageble=new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
 
        //model.setListResult(customerService.findAll(offset,model.getMaxPageItem()));
        model.setListResult(customerService.findAll(pageble));
        model.setTotalItem(customerService.getTotalItem());
        model.setTotalPage((int)Math.ceil((double)model.getTotalItem()/model.getMaxPageItem()));
        
        
		request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/customer/list.jsp");
        rd.forward(request, response);
		
	
	
	}

}
