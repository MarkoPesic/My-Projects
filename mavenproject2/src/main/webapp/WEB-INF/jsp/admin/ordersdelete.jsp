<%-- 
    Document   : ordersdelete
    Created on : Aug 10, 2018, 6:05:24 PM
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
    <p>Confirm deleting</p>
    <body>
            <form method="post" action="ordersdelete">
            <input type="hidden" name="id" value="${orders.id}" />
           <input type="submit" value="delete " />
    </body>
</html>
