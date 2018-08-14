

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
        <form method="post" action="addshoe" enctype="multipart/form-data">
            Name: <input type="text"  name="shoename" /><br />
            Price: <input type="number"  name="shoeprice" /><br /><br />
            Size: <input type="number"  name="shoesize" /><br /><br />
            Set Category(write ID): <input class="textbox" type="text" name="shoecategories" required>
            <input type="file" name="photo" />
            <input type="submit" name="update" value="Add Shoe" />
            <p>All Categories</p>
            <p><c:forEach items="${categories}" var="category">
            <p>Category ID: ${category.id} Category Name:${category.name}</p>      
                    
            </c:forEach></p>
        </form>
    </body>
</html>
