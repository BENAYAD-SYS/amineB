package com.xprodmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xprodmvc.model.Users;

public class UsersDAO{
	
	private Connection con;
	
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public UsersDAO(Connection con) {
		this.con = con;
	}
	
	public Users userLog(String email, String password) {
		
		Users user = null;
		try {
		query = "select * from user where email = ? and mdp = ?";
		pst = this.con.prepareStatement(query);
		pst.setString(1,  email);
		pst.setString(2, password);
		rs = pst.executeQuery();
		
		if(rs.next()) {
			user = new Users();
			
			user.setID_USERS(rs.getInt("iduser"));
			user.setNOM(rs.getString("name"));
			user.setEMAIL(email);
		}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
}