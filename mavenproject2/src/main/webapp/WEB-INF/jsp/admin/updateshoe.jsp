

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <form method="post" action="updateshoe" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${shoe.id}" />
            Name: <input type="text" name="name" value="${shoe.name}" /><br />
            Price: <input type="number" name="price" value="${shoe.price}" /><br />
            <select name="category"> 
                <c:forEach items="${categories}" var="category">
                    <option <c:if test="${category.id==shoe.category}">selected</c:if> value="${category.id}">${category.name}</option>
                </c:forEach>
            </select><br />
            <input type="file" name="photo" />
            <input type="submit" name="update" value="Update Shoe" />
        </form>
    </body>
</html>
