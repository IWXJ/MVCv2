<%-- 
    Document   : registeruser
    Created on : 10-03-2015, 17:54:51
    Author     : OBS
--%>

<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <script type="text/javascript">
        function validateZipcode() {
            var x = document.getElementById('zipcode').value;
            if(isNaN(x)) {
                alert("Only numbers are allowed.");
                return false;
            }
            
            if(x < 1000 || x > 9990) {
                alert("Zipcode must be between 1000 and 9990.");
                return false;
            }
            return true;
        }
    </script>

    <div class="rightMenu">
        <p><h1>Register</h1>
        <p>Please enter your information in the form below and click 'Submit'.
        <p>
        <form method="POST" action="registerUser.do">
            <table>
                <tr><td>E-mail: </td><td><input type="text" name="email"></td></tr>
                <tr><td>Password: </td><td><input type="password" name="password"></td></tr>
                <tr><td>Name: </td><td><input type="text" name="name"></td></tr>
                <tr><td>Address: </td><td><input type="text" name="address"></td></tr>
                <tr><td>Zip code: </td><td><input onkeydown="validateZipcode()" type="text" name="zipcode"></td></tr>
                <tr><td>City: </td><td><input type="text" name="city"></td></tr>
                <tr><td>Phone: </td><td><input type="text" name="phone"></td></tr>
            </table>
            <input type="submit" value="Register">
        </form>
    </div>
