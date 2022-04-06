<%@ page import="com.xprodmvc.connection.dbCon"%>
<%@ page import="com.xprodmvc.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.xprodmvc.dao.productsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Users auth = (Users) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth", auth);
}
productsDAO pd = new productsDAO(dbCon.getConnection());
List<products> products = pd.getAllProducts();

ArrayList<cart> cart_list = (ArrayList<cart>)session.getAttribute("cart-list");
List<cart> cartProduct = null;

if (cart_list != null) {
    cartProduct=pd.getCardProducts(cart_list);
    request.setAttribute("cart_list", cart_list);

}
%>
<!DOCTYPE html>
<html>
<head>
<title>XPROD index</title>
<%@include file="incloudes/head.jsp"%>
</head>

<body>
	<%@include file="incloudes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">List of product</div>
		<div class="row">

			<%
			if (!products.isEmpty()) {

				for (products p : products) {
			%>
			<div class="col-md-3" style="width:18rem; height: 400px;">
				<div class="card" style="width:16rem; height: 350px;">
					<img class="card-img-top" src="product-image/<%=p.getIMAGE()%>"
						align="center"; style="width:250px; height: 200px;">
					<div class="card-body">
						<h5 class="card-title"><%=p.getNOM()%></h5>
						<h6 class="price"><%=p.getPRIX()%></h6>
						<h6 class="category"><%=p.getCATEGORIE()%></h6>
						<div class="mt-3 d-flex justify content between"></div>
						<a href="add-to-cart-servlet?idproducts=<%=p.getIDPROD() %>" class="btn btn-dark">Add to card</a> 
						<a href="order-now?quantity=1&idproducts=<%=p.getIDPROD() %>" class="btn btn-primary">Buy now</a>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>

	<%@include file="incloudes/footer.jsp"%>
</body>
</html>