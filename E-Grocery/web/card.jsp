<%-- 
    Document   : card
    Created on : Jun 11, 2018, 2:54:06 PM
    Author     : Marko
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shop</title>
        <link rel="stylesheet" href="login.css">
    </head>
    <body>
        <div class="wrapper"> 
            
                        <c:if test="${status==0}">
              <header>
                <img src="Pictures/logo.png">
                <p>The best place for shopping online</p>
                 <ul>
                    <li>
                        <a class="nav-dugme" href="Products">Products</a>
                        <a class="nav-dugme" >Your Cart</a>
                        <a class="nav-dugme" href="previousbuy">Last Orders</a>
                    </li>
                      <li>
                          <a class="nav-dugme" href="accprofile.jsp">${username}</a>
                          <a class="nav-dugme" href="logout">Log Out</a>
                    </li>
                </ul>
                 </header> 
                </c:if>
                
                
                <c:if test="${status==1}">
                  <header>
                 <img src="Pictures/logo.png">
                <p>The best place for shopping online</p>
                <ul> <li>
                        <a class="nav-dugme" href="Products">Products</a>
                        <a class="nav-dugme" >Your Cart</a>
                        <a class="nav-dugme" href="previousbuy">Last Orders</a>
                    </li>
                      <li>
                          <a class="nav-dugme" href="accprofile.jsp">${username}</a>
                          <a class="nav-dugme" href="adminpage.jsp">Admin Page</a>
                          <a class="nav-dugme" href="logout">Log Out</a>
                    </li>
                </ul>
                 </header> 
                </c:if>
             
            <div class="content">
                <h3>Your Basket</h3>
                <c:forEach items="${products}" var="product">
                    <div class="product">${product.name} (${product.quantity}) <a class="label2" href='?remove=${product.id}'>Remove</a></div>
                </c:forEach>
                <input class="button" type="button" value="Checkout" onclick="window.location='./card?checkout=1'"/>
                <p>Total bill: ${bill} RSD</p>
                <c:if test="${param.checkout==1}">
                <form action="card" method="post">
                    Adress and City:<br>
                   <textarea class="textbox" name="address" required ></textarea>  <br>
                   <input class="button" type="submit" name="doCheckout" value="Order">
               </form>
            </c:if>
            </div>
        </div>
        
        
        
        
        

    </body>
</html>