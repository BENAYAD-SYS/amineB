package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xprodmvc.connection.dbCon;
import com.xprodmvc.dao.orderDAO;
import com.xprodmvc.model.Users;
import com.xprodmvc.model.cart;
import com.xprodmvc.model.order;


@WebServlet(name = "card-check-out", urlPatterns = { "/card-check-out" })
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

		try(PrintWriter out = response.getWriter()) {
			//out.println("chek out");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			HttpSession session = request.getSession();
			 ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
			 Users auth = (Users) request.getSession().getAttribute("auth");
			 
			 if(cart_list != null && auth!=null) {
				 
				 for(cart c : cart_list) {
					 order order = new order();
					 order.setIDPROD(c.getIDPROD());
					 order.setID_USER(auth.getID_USERS());
					 order.setQUANTITY(c.getQUANTITY());
					 order.setDATE(formatter.format(date));
					 
					 orderDAO odao = new orderDAO(dbCon.getConnection());
					 boolean result = odao.insertOrder(order);
					 
					 if(!result) break;
				 }
				 cart_list.clear();
				 response.sendRedirect("orders.jsp");
			 }else {
				 if(auth==null) {
					 response.sendRedirect("login.jsp");
				 }
				 response.sendRedirect("card.jsp");
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
