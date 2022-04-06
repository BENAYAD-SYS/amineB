package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xprodmvc.connection.dbCon;
import com.xprodmvc.dao.UsersDAO;
import com.xprodmvc.model.Users;


@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html/charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			//out.print("this is login servlet. ");
			
			String email = request.getParameter("login-mail");
			String password = request.getParameter("login-password");
			//out.print("This are the servlet infos: "+email+", "+password);
			UsersDAO udao = new UsersDAO(dbCon.getConnection());
			Users user = udao.userLog(email, password);
			
			if(user!=null) {
				request.getSession().setAttribute("auth", user);
				System.out.print("user : "+email+"logged in");
				response.sendRedirect("index.jsp");
			}else {
				System.out.print("no");
			}
			} 
		    catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}

}
