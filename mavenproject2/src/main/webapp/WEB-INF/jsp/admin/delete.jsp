<%-- 
    Document   : delete
    Created on : Aug 10, 2018, 5:19:35 PM
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <form method="post" action="delete">
    <input type="hidden" name="id" value="${shoe.id}" />
    Picture: <img src="../resources/images/${shoe.photo}" /> <br />
    Name: <input type="text" name="name" value="${shoe.name}" /><br />
    Price: <input type="number" name="price" value="${shoe.price}" /><br />
     <input type="submit" value="delete " />
      </form>
    </body>
</html>
