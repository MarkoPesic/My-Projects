<%-- 
    Document   : adminpage
    Created on : Jul 4, 2018, 4:00:05 PM
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
if(session.getAttribute("username")==null  | session.getAttribute("status").equals("0")) {
%>
<jsp:include page="login.jsp"></jsp:include>
<%
    } else {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shop</title>
         <link rel="stylesheet" href="login.css">
    </head>
    <body>
        <div class="wrapper"> 
              <header>
                <img src="Pictures/logo.png">
                <p>The best place for shopping online</p>
                <ul> <li>
                        <a class="nav-dugme" href="Products">Products</a>
                          <c:if test="${cardCount>0}">
                            <a class="nav-dugme" href="card">Your Cart</a>
                          </c:if>
                           <c:if test="${cardCount==0}">
                            <a class="nav-dugme" >Your Cart</a>
                          </c:if>
                        <a class="nav-dugme" href="previousbuy">Last Orders</a>
                    </li>
                      <li>
                          <a class="nav-dugme" href="accprofile.jsp">${username}</a>
                          <a class="nav-dugme" >Admin Page</a>
                          <a class="nav-dugme" href="logout">Log Out</a>
                    </li>
                </ul>
                 </header> 
                    
               <div>
                   
                              <%--     Add Product,Delete and add Quantity        --%>
                <div class="content ">
               <h3>Add New Products</h3>
               <h4>Enter products details.</h4>
               <form method="post" action="adminpage">
                   <div class="label">Products name:</div><input class="textbox" type="text" name="productname" required><br>
                   <div class="label">Product price:</div><input class="textbox" type="text" name="productprice" required><br>
                   <div class="label">Product available:</div><input class="textbox" type="text" name="pavailable" required><br>
                   <div class="label">Product picture(link):</div><input class="textbox" type="text" name="picture" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Add"><br>
               </form>
               <hr>
               <h3>Add Products Quantity</h3>
                  <h4>Enter name of the product that you want to add quantity.</h4>
               <form method="post" action="adminpage">
                   <div class="label">Product name:</div><input class="textbox" type="text" name="ddproductname" required><br>
                   <div class="label">Product available:</div><input class="textbox" type="text" name="productquantity" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Add"><br>
               </form>
               
               <hr>
                  <h3>Delete Product</h3>
                  <h4>Enter name of the product that you want to delete.</h4>
               <form method="post" action="adminpage">
                   <div class="label">Product name:</div><input class="textbox" type="text" name="dproductname" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Delete"><br>
               </form>
                  
              </div>
                   
                   
                                   <%--     Add Admin and Remove Admin       --%>
              <div class="content">
                  <h3>Make Someone Admin</h3>
                  <h4>Enter username of user that you want to make admin.</h4>
               <form method="post" action="adminpage">
                   <div class="label">Username:</div><input class="textbox" type="text" name="ausername" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Make"><br>
               </form>
                  <hr>
                  <h3>Remove Someone from Admin</h3>
                  <h4>Enter username of user that you want to remove from admin.</h4>
               <form method="post" action="adminpage">
                   <div class="label">Username:</div><input class="textbox" type="text" name="rusername" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Remove"><br>
               </form>
              </div>   
                
                   
                   
                    <%--     Complete Orders and See All Orders         --%>
                   <div class="content">
                  <h3>See All Uncompleted Orders.<h3>
                   <h4>Note: First see all uncompleted orders!!</h4>
                   <form method="post" action="ordersuncompleted">
                <input style="margin-top:15px;" class="button" type="submit" value="See"><br>
               </form>
                   <hr>
                  <h3>Complete Orders</h3>
                  <h4>Enter Orders ID to complete.</h4>
               <form method="post" action="adminpage">
                   <div class="label">Order ID:</div><input class="textbox" type="text" name="orderid" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Complete Order"><br>
               </form>
              </div> 
                                       
                                       
                                       
            </div>
                                 
    </body>
</html>
        <%
}%>   
