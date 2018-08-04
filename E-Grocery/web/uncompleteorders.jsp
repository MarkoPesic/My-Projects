<%-- 
    Document   : uncompleteorders
    Created on : Jul 7, 2018, 5:52:32 PM
    Author     : Marko
--%>

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
                      <c:if test="${cardCount==0}">
                    <a class="nav-dugme" >Your Cart</a>
                          </c:if>
                           <c:if test="${cardCount>0}">
                            <a class="nav-dugme" href="card">Your Cart</a>
                          </c:if>
                        <a class="nav-dugme" href="card">Your Cart</a>
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
      
            <div>
                <div class="content">
                <h2>Uncomplete Orders</h2>
                <h3></h3>
        <table  border="1">
            <tr>
                <th>ORDER ID </th>
                <th>USERNAME </th> 
                <th>ADDRESS </th> 
                <th>QUANTITY </th> 
                <th>PRODUCT NAME </th>
                <th>BILL</th>
                <th>ORDER STATUS </th>
            </tr>
            <c:forEach items="${kupljeno}" var="kupljenoo">
                <tr>
                <td>   ${kupljenoo.id}</td>
                <td>   ${kupljenoo.username}</td>
                <td>   ${kupljenoo.address}</td>
                <td>   ${kupljenoo.quantity} </td>
                 <td>  ${kupljenoo.name} </td>
                 <td > ${kupljenoo.bill} RSD</td>
                 <td>  ${kupljenoo.orderstatus} </td>
                 </tr>
                 </c:forEach> 
                   </table>
                  </div> 

            </div>
        </div>
</body>
