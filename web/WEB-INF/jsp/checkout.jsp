<%-- 
    Document   : checkout
    Created on : 10-03-2015, 17:52:03
    Author     : OBS
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <div class="rightMenu">
        <p><h1>Your order</h1>
        <p>Please verify your order before clicking 'Confirm'. Click 'Delete order' if you wish to change your order (you will have to start all over again).
        <p>
            <table border="1" cellpadding="10" cellspacing="20">
                <tr>
                    <th>Quantity</th>
                    <th>Item</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${itemList}" var="item">
                        <tr><td>${item.quantity}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                </tr>							
                </c:forEach>
                <tr>
                    <th colspan="2" align="left">Total price: </th>
                    <td>${orderTotalPrice}</td>
                </tr>
            </table>
        <p>
            Thank you for shopping with us!
        </p>
    </div>
