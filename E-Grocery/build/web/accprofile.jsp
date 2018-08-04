<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
if(session.getAttribute("username")==null) {
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
                        <a class="nav-dugme" href="previousbuy">Last Orders</a>
                    </li>
                      <li>
                          <a class="nav-dugme" >${username}</a>
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
                          <a class="nav-dugme" >${username}</a>
                          <a class="nav-dugme" href="adminpage.jsp">Admin Page</a>
                          <a class="nav-dugme" href="logout">Log Out</a>
                    </li>
                </ul>
                 </header> 
                </c:if>
      
            <div>
                <div class="content">
               <h3>Your are logged in as: ${username}</h3>
              <h4>Enter your new Password!</h4>
               <form method="post" action="accprofile" name="accprofile" onsubmit="return checking()">
                <div class="label">New Password:</div><input class="textbox" maxlength="12" type="password" name="password" required><br>
                 <div class="label">Retype Password</div><input class="textbox" maxlength="12" type="password" name="retypePassword" required><br>
                <input style="margin-top:15px;" class="button" type="submit" value="Change"><br>
       </form>
                <script>   
                    function checking() {  
                       
                        /*PROVERA PASSWORDA*/
                        var pass = document.forms["accprofile"]["password"].value;
                        var retypePass = document.forms["accprofile"]["retypePassword"].value;
                        
                        if (pass !== retypePass) {
                            alert("Password must match!");
                            return false;
                        }
                    }
                </script>

                  </div> 
            </div>
        </div>
</body>
        <%
}%>  