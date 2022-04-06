package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xprodmvc.connection.dbCon;
import com.xprodmvc.dao.orderDAO;
import com.xprodmvc.model.Users;
import com.xprodmvc.model.cart;
import com.xprodmvc.model.order;

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet(name = "order-now", urlPatterns = { "/order-now" })
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			
			Users auth= (Users) request.getSession().getAttribute("auth");
			if(auth!=null) {
				String productId = request.getParameter("idproducts");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity<=0) {
					productQuantity = 1;
				}
				
				order orderModel = new order();
				
				orderModel.setIDPROD(Integer.parseInt(productId));
				orderModel.setID_USER(auth.getID_USERS());
				orderModel.setQUANTITY(productQuantity);
				orderModel.setDATE(formatter.format(date));
				
				orderDAO orderDAO = new orderDAO(dbCon.getConnection());
				boolean result = orderDAO.insertOrder(orderModel);
				
				if(result) {
					ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list!=null) {
                        for (cart c : cart_list) {
                            if (c.getIDPROD() == Integer.parseInt(productId)) {

                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
					response.sendRedirect("orders.jsp");
				}
				else {
					out.println("orders failed");
					}
				}else {
					response.sendRedirect("login.jsp");
				}
			}
				
			
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
