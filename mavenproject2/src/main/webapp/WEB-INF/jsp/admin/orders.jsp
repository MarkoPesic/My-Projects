<%-- 
    Document   : orders
    Created on : Aug 7, 2018, 7:03:45 PM
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <h1>ALL ORDERS!</h1>
        <table border="1">
            <tr><td>id</td><td>User Data</td><td>Products</td><td>Order time</td><td></td></tr>
          <c:forEach items="${orders}" var="order">
                <tr><td>${order.userdata}</td><td>${order.products}</td><td>${order.ordertime}</td><td><a href="ordersdelete?id=${order.id}">delete</a></td></tr>
            </c:forEach>
           </table>
<!--        <form method="post" action="orders" >
          Enter Order ID: <input type="number"  name="id" /><br />
            <input type="submit"  value="Delete" />
        </form>-->
    </body>
</html>
