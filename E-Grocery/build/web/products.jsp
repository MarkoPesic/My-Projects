<%-- 
    Document   : products
    Created on : Jun 6, 2018, 7:28:57 PM
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
        
        <%--  If you are logged in as user --%>
        <div class="wrapper"> 
            <c:if test="${status==0}">
              <header>
                <img src="Pictures/logo.png">
                <p>The best place for shopping online</p>
                 <ul>
                    <li>
                        <a class="nav-dugme">Products</a>
                    <c:if test="${cardCount==0}">
                    <a class="nav-dugme" >Your Cart</a>
                          </c:if>
                          <c:if test="${cardCount>0}">
                            <a class="nav-dugme" href="card">Your Cart</a>
                          </c:if>
                    <a class="nav-dugme" href="previousbuy">Last Orders</a>
                    </li>
                      <li>
                          <a class="nav-dugme" href="accprofile.jsp">${username}</a>
                          <a class="nav-dugme" href="logout">Log Out</a>
                    </li>
                </ul>
                 </header> 
                </c:if>
                
                 <%--  If you are logged in as admin --%>
                <c:if test="${status==1}">
                  <header>
                 <img src="Pictures/logo.png">
                <p>The best place for shopping online</p>
                <ul> <li>
                        <a class="nav-dugme">Products</a>
                         <c:if test="${cardCount==0}">
                    <a class="nav-dugme" >Your Cart</a>
                          </c:if>
                     <c:if test="${cardCount>0}">
                            <a class="nav-dugme" href="card">Your Cart</a>
                          </c:if>
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
                
                  <c:if test="${cardCount==0}">
                     <p class="label" > <h3>Your cart is empty</h3></p>
                </c:if>
                <c:if test="${cardCount!=0}">
                    <p class="label" > <h3>Products in cart: ${cardCount}</h3></p>
                </c:if>          
            <div class="content">    

                <c:forEach items="${products}" var="product">
                    <img class="slika" src="${product.picture}">
                    <div>Product name:<b style="color: red"> ${product.name}</b>  </div>
                    <div>Product price: ${product.price} RSD</div>
                    <c:if test="${product.quantity==0}">
                    <div>Products avaliavable:No more of this product in shop</div>
                    <div><a class="label2"><img class="korpa" src="Pictures/korpa.png"></a></div>
                    </c:if>
                    
                     <c:if test="${product.quantity>0}">
                     <div>Products avaliavable: ${product.quantity}</div>
                     <div><a class="label2" href='?add=${product.id}'><img class="korpa" src="Pictures/korpa.png"></a></div>
                    </c:if>
                     <hr>
                </c:forEach>
                                                            
            </div>
        </div>
    </body>
    