<%@page import="com.xprodmvc.dao.orderDAO"%>
<%@page import="com.xprodmvc.model.order"%>
<%@page import="com.xprodmvc.model.Users"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.xprodmvc.connection.dbCon"%>
<%@page import="com.xprodmvc.dao.productsDAO"%>
<%@page import="com.xprodmvc.model.cart"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

Users auth = (Users) request.getSession().getAttribute("auth");

List<order> orders = null;

if (auth != null) {
	request.setAttribute("person", auth);
	orderDAO orderdao = new orderDAO(dbCon.getConnection());
	orders = orderdao.userOrder(auth.getID_USERS());
} else {
	//response.sendRedirect("login.jsp");
}
ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
//List<cart> cartProduct = null;

if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);

}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="incloudes/head.jsp"%>
</head>
<body>
	<%@include file="incloudes/navbar.jsp"%>
	<div class="container">
	<div class="cart-header my-3">All orders</div>
	<table class="table table-light">
	<thead>
	<tr>
	<th scope="col">Date</th>
	<th scope="col">Name</th>
	<th scope="col">Category</th>
	<th scope="col">Quantity</th>
	<th scope="col">Price</th>
	<th scope="col">Cancel</th>
	</tr>
	</thead>
	<tbody>
	<%
	if (orders != null){
		for(order o: orders){%>
		<tr>
		<td><%=o.getDATE() %></td>
		<td><%=o.getNOM() %></td>
		<td><%=o.getCATEGORIE() %></td>	
		<td><%=o.getQUANTITY() %></td>
		<td><%=dcf.format(o.getPRIX()) %></td>
		<td><a class="btn btn-sm btn-danger"
		href="cancel-order?id=<%=o.getID_ORDER() %>">Annuler la commande</a></td>
		</tr>
		<%}
	}
	%>
	</tbody>
	</table>
	</div>
	<%@include file="incloudes/footer.jsp"%>
</body>
</html>