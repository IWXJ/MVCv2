<%-- 
    Document   : login
    Created on : 10-03-2015, 17:53:15
    Author     : OBS
--%>

<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <script type="text/javascript" src="../js/validation.js"/>

    <div class="rightMenu">
        <p><h1>Login</h1>
        <p>Enter your e-mail address and password below.
           <%if ("loginError".equals(session.getAttribute("loginError"))) {%>
                <p>Error in username or password. Please try again.</p>
           <%}%>
        <p>
        <form method="POST" action="login.do">
                <table>
                    <tr><td>E-mail: </td><td><input type="text" name="email" onchange="validateEmail()"></td></tr>
                    <tr><td>Password: </td><td><input type="password" name="password"></td></tr>
                </table>
                <input type="submit" value="Login">
        </form>
    </div>
