<html>
    <head>
        <title>Register Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="login.css" >
    </head>
    <body>
        <div class="wrapper">
            <div  class="form">
                <form action="register" method="post" name="register" onsubmit="return checking()"> 
                    <img class="loginLogo" src="Pictures/logo.png">
                    <div class="label">Username</div><input class="textbox" type="text" name="username" required><br>
                    <div class="label">Password</div><input class="textbox" maxlength="12" type="password" name="password" required><br>
                    <div class="label">Retype Password</div><input class="textbox" maxlength="12" type="password" name="retypePassword" required><br>
                    <div title="Type 4 digit number in box below to prove you are not a robot."
                         style="margin-right:115px;" id="pin" class="label">Pin:</div><input style="width:60px;" class="textbox" maxlength="4" type="text" name="pin">
                    <input style="margin-left:10px; width:100px;" class="button" type="submit" value="Register">
                    <a class="label" href="login.jsp">Already have account? Login here</a>
                    
                </form>
                         
                 <script>
                     
                    var randomPin = Math.floor((Math.random() * 10)).toString() 
                    +  Math.floor((Math.random() * 10)).toString() 
                    +  Math.floor((Math.random() * 10)).toString() 
                    +  Math.floor((Math.random() * 10)).toString();
                    document.getElementById("pin").innerHTML = "Pin:" + randomPin;
                   
                    function checking() {  
                        
                        /*PROVERA PIN KODA*/
                        var regPin = document.forms["register"]["pin"].value;
                        
                        if (regPin !== randomPin) {
                            alert("Pleas fill out correct pin code.");
                            return false;
                        }

                        
                        /*PROVERA PASSWORDA*/
                        var pass = document.forms["register"]["password"].value;
                        var retypePass = document.forms["register"]["retypePassword"].value;
                        
                        if (pass !== retypePass) {
                            alert("Password must match!");
                            return false;
                        }
                    }
                </script>
             
            </div>
        </div>
    </body>
</html>