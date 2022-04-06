package com.xprodmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xprodmvc.model.*;

public class productsDAO {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public productsDAO(Connection con) {
		this.con = con;
	}

	/*
	 * public productsDAO(Connection con, String query, PreparedStatement pst,
	 * ResultSet rs) { this.con = con; this.query = query; this.pst = pst; this.rs =
	 * rs; }
	 */
	public productsDAO() {
	}

	public List<products> getAllProducts() {
		List<products> products = new ArrayList<products>();
		try {
			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				products row = new products();
				row.setIDPROD(rs.getInt("idproducts"));
				row.setNOM(rs.getString("nom"));
				row.setCATEGORIE(rs.getString("categorie"));
				row.setPRIX(rs.getDouble("prix"));
				row.setIMAGE(rs.getString("image"));

				products.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public List<cart> getCardProducts(ArrayList<cart> cardlist) {
		List<cart> products = new ArrayList<>();
		try {
			if (cardlist.size() > 0) {

				for (cart item : cardlist) {

					query = "select * from products where idproducts=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getIDPROD());
					rs = pst.executeQuery();
					while (rs.next()) {

						cart row = new cart();
						row.setIDPROD(rs.getInt("idproducts"));
						row.setNOM(rs.getString("nom"));
						row.setCATEGORIE(rs.getString("categorie"));
						row.setIMAGE(rs.getString("image"));
						row.setPRIX(rs.getDouble("prix") * item.getQUANTITY());
						row.setQUANTITY(item.getQUANTITY());

						products.add(row);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public double getTotalCartPrice(ArrayList<cart> cartlist) {
		double sum = 0;

		try {
			if (cartlist.size() > 0) {
				for (cart item : cartlist) {
					query = "select prix from products where idproducts=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getIDPROD());
					rs = pst.executeQuery();
					while (rs.next()) {
						sum += rs.getDouble("prix") * item.getQUANTITY();
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	public products getSingleProduct(int pID) {
		products row = null;
		try {
			query ="select * from products where idproducts=?";
			
			pst = this.con.prepareStatement(query);
			pst.setInt(1, pID);
			ResultSet rs =pst.executeQuery();
			
			while(rs.next()) {
				row = new products();
				row.setIDPROD(rs.getInt("idproducts"));
				row.setNOM(rs.getString("nom"));
				row.setCATEGORIE(rs.getString("categorie"));
					row.setPRIX(rs.getDouble("prix"));
					row.setIMAGE(rs.getString("image"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
}