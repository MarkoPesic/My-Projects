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
        <table border="1">
            <tr><td>id</td><td>title</td><td>price</td><td>photo</td><td></td></tr>
            <c:forEach items="${shoes}" var="shoe">
                <tr><td>${shoe.id}</td><td>${shoe.name}</td><td>${shoe.price}</td><td><img src="../resources/images/${shoe.photo}" /></td><td><a href="updateshoe?id=${shoe.id}">edit</a></td><td><a href="delete?id=${shoe.id}">delete</a></td></tr>
            </c:forEach>
                <tr><td colspan="5"> 
                        <c:forEach begin="1" end="${totalpages}" varStatus="counter">
                            <a href="shoes?page=${counter.count}">${counter.count}</a>
                        </c:forEach> 
            </td></tr>
        </table>    
    </body>
</html>
