<%-- 
    Document   : login
    Created on : 10-03-2015, 17:53:15
    Author     : OBS
--%>

<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <div class="rightMenu">
        <p><h1>Login</h1>
        <p>Enter your e-mail address and password below.
           <%if ("loginError".equals(session.getAttribute("loginError"))) {%>
                <p>Error in username or password. Please try again.</p>
           <%}%>
        <p>
        <form method="POST" action="login.do" onsubmit='return true;'>
                <table>
                    <tr><td>E-mail: </td><td><input type="text" name="email" onchange="validateEmail(this)"></td></tr>
                    <tr><td>Password: </td><td><input type="password" name="password"></td></tr>s
                </table>
            <input type="submit" onclick="return processLoginForm();" value="Login">
        </form>
<!--        <div id='loginFormTag'>
            
        </div>
-->        <div id="resultLoginForm">

        </div>
    </div>
<!--        <script>
            window.onload = showLoginForm;
        </script>-->
        <script src="../../jquery-1.11.0.js" type="text/javascript"></script>
            <script type="text/javascript">
                function processLoginForm() { 
                        window.alert("Start of method");
                        $.ajax( {
                            type: 'POST',
                            url: 'login.do?email=' 
                                + encodeURIComponent(document.getElementById('email').value) + 'password=' 
                                + encodeURIComponent(document.getElementById('password').value),
                            success: function(result) {
                                $('#resultLoginForm').html(result);
                                window.alert("End of method");
                                return false;
                            }
                        } );
                    window.alert("End of method");
                    return false;
                }
            </script>

