<%-- 
    Document   : cart
    Created on : 10-03-2015, 17:51:37
    Author     : OBS
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <div class="rightMenu">
        <p><h1>Your order list</h1>
        <p>You may update the number of items or remove items from the list.
        <p> <table border="1" cellpadding="10" cellspacing="20">
                <tr>
                    <th>Quantity</th>
                    <th>Add</th>
                    <th>Remove</th>
                    <th>Item</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${cartList}" var="item">
                    <tr>
                    <td><form method="POST" action="addToCart.do?${item.name}">
                            <input type="submit" value="Add"></form></td>
                    <td><form method="POST" action="removeFromCart.do?${item.name}">
                            <input type="submit" value="Remove"></form></td>
                            <td>${item.quantity}</td>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                    </tr>							
                </c:forEach>
        </table>
    </div>
