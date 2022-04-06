<%@ page import="com.xprodmvc.model.*"%>
<%@ page import="com.xprodmvc.dao.productsDAO"%>
<%@ page import="com.xprodmvc.connection.dbCon"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Users auth = (Users) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}

ArrayList<cart> cart_list = (ArrayList<cart>)session.getAttribute("cart-list");
List<cart> cartProduct = null;

if (cart_list != null) {
    productsDAO pDao = new productsDAO(dbCon.getConnection());
   
    cartProduct=pDao.getCardProducts(cart_list);
    double total = pDao.getTotalCartPrice(cart_list);
    request.setAttribute("total", total);
    request.setAttribute("cart_list", cart_list);

}
%>
<!DOCTYPE html>
<html>
<head>
<title>XPROD cart</title>
<style type="text/css">
.table body td {
    vertical align: middle:
}

.btn-incre, btn-decre {
    box-shadow: none;
    font-size: 25px;
}
}
</style>
<%@include file="incloudes/head.jsp"%>
</head>

<body>
    <%@include file="incloudes/navbar.jsp"%>
    <h1>PANIER</h1>
    <div class="container">
        <div class="d-flex py-3">
       
            <h3>Prix : ${total} €</h3>
            <a class="mx-3 btn btn-primary" href="card-check-out">Valider la
                Commande</a>

        </div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Nom></th>
                    <th scope="col">Categorie></th>
                    <th scope="col">Prix></th>
                    <th scope="col">Acheter maintenant></th>
                    <th scope="col">Annuler></th>
                </tr>
            </thead>
            <tbody>
<%

            if(cart_list != null){
               for(cart c : cartProduct){
%>

                <tr>
                    <td><%=c.getNOM() %></td>
                    <td><%=c.getCATEGORIE() %></td>
                    <td><%=c.getPRIX() %></td>
                    <td>
                        <form action="order-now" method="post" class="form-inline">
                            <input type="hidden" name="idproducts" value="<%=c.getIDPROD() %>" class="form-input">
                            <div class="form-group d-flex justify-content-between">
                            <input type="text" name="quantity" class="form-control-sm"
                            value="<%=c.getQUANTITY() %>" read only>
                                <a class="btn btn-sm btn-incre" href="quantity-inc-dec-servlet?action=inc&id=<%=c.getIDPROD()%>">
                                <i class="fas fa-plus-square"></i>
                                </a> 
                                <a class="btn btn-sm btn-decre" href="quantity-inc-dec-servlet?action=dec&id=<%=c.getIDPROD()%>"><i
                                    class="fas fa-minus-square"></i></a>
                            </div></td><td>
                    <button  class="btn btn-paye btn-sm" href="order-now">Payer</button></form>
                    </td><td>
                    <a href="remove-from-cart?action=sup&id=<%=c.getIDPROD() %>" class="btn btn-on btn-danger">Enlever</a></td>
                </tr>
            <%}
                } %>



            </tbody>

        </table>
    </div>

    <%@include file="incloudes/footer.jsp"%>
</body>
</html>