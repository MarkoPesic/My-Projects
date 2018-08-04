<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="login.css" >
    </head>
    <body>
        <div class="wrapper">
            <div class="form">
               <form method="post" action="login">
                    <img class="loginLogo" src="Pictures/logo.png">
                    <div class="label">Username</div><input class="textbox" type="text" name="username" required><br>
                    <div class="label">Password</div><input class="textbox" maxlength="12" type="password" name="password" required><br>
                    <input style="margin-top:15px;" class="button" type="submit" value="Login"><br>
                    <a class="label" href="register.jsp">Register</a>
                </form>
            </div>
        </div>
    </body>
</html>