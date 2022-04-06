package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xprodmvc.model.cart;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet(name = "quantity-inc-dec-servlet", urlPatterns = { "/quantity-inc-dec-servlet" })
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuantityIncDecServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// PrintWriter out = response.getWriter();
		// out.println("Inc dec servlet");
		response.setContentType("text/html/charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (cart c : cart_list) {
						if (c.getIDPROD() == id) {
							int quantity = c.getQUANTITY();
							quantity++;
							c.setQUANTITY(quantity);
							response.sendRedirect("card.jsp");
						}
					}
				}
				if (action.equals("dec")) {
					for (cart c : cart_list) {
						if (c.getIDPROD() == id && c.getQUANTITY() > 0) {
							int quantity = c.getQUANTITY();
							quantity--;
							c.setQUANTITY(quantity);
							break;
						}
					}
					response.sendRedirect("card.jsp");
				}
			} else {
				response.sendRedirect("card.jsp");
			}
		}
	}

}
