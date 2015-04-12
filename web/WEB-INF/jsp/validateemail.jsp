<%-- 
    Document   : validateemail
    Created on : 22-03-2015, 15:32:28
    Author     : OBS
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <div class="rightMenu">
        <p><h1>Validate your e-mail</h1>
        <p>Please click the link below to confirm your user.
        <p><a href="/LaPizzeriaV2/validateemail.do?email=${customer.email}">Click here</a>
    </div>

