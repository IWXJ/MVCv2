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
        <p>You may update the number of items or remove items from the list.</p>
        <p> <table border="1" cellpadding="10" cellspacing="20">
                <tr>
                    <th>Quantity</th>
                    <th>Item</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${orderList.orderedProductList}" var="item">
                    <tr>
                        <td><form method="POST" action="updateProductQuantity.do">
                                <input type="submit" value="Update">
                                <input type="hidden" name="pizzaId" value="${item.pizzaId}">
                                <input type="text" maxlength="2" size="2" name="quantity" value="${item.quantity}">
                            </form>
                        </td>
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.price}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="3" align="left">Total price: </th>
                    <td>${orderList.orderTotal}</td>
                </tr>
            </table>
            <br>
            <br>
            <table border="0">
                <tr>
                    <td><a href="/LaPizzeriaV2/cancelOrder.do">Cancel order</a></td>
                    <td><a href="/LaPizzeriaV2/confirmOrder.do">Confirm order</a></td>
                </tr>
            </table>
    </div>
