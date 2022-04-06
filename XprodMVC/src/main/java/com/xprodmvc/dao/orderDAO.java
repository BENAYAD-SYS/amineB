package com.xprodmvc.dao;
import com.xprodmvc.model.products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xprodmvc.model.*;

public class orderDAO {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
    
	public orderDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean insertOrder(order model) {
		boolean result = false;
		try {
			query = "insert into orders(idproducts,iduser, quantity, dateorder) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getIDPROD());
			pst.setInt(2, model.getID_USER());
			pst.setInt(3, model.getQUANTITY());
			pst.setString(4, model.getDATE());
			
			pst.executeUpdate();
			
			result = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
		
	}
	
	public List<order> userOrder(int id){
		List<order> list = new ArrayList<>();
		
		
		try {
			query ="select * from orders where iduser=? order by orders.idorder desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				order order = new order();
				productsDAO productDao = new productsDAO(this.con);
				int pID = rs.getInt("idproducts");
				products product = productDao.getSingleProduct(pID);
				
				order.setID_ORDER(rs.getInt("idorder"));
				order.setIDPROD(pID);
				order.setNOM(product.getNOM());
				order.setCATEGORIE(product.getCATEGORIE());
				order.setPRIX(product.getPRIX());
				order.setQUANTITY(rs.getInt("quantity"));
				order.setDATE(rs.getString("dateorder"));
				
				list.add(order);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void cancelOrder(int id) {
		try {
			query="delete from orders where idorder=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}