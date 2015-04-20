<%-- 
    Document   : menuadmin
    Created on : 10-03-2015, 17:54:26
    Author     : OBS
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=windows-1252" 
	pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <div class="rightMenu">
        <p><h1>Menu</h1>
        <p>Add entries to the list.
        <br>Delete entries by clicking 'Delete' in the list below.
        <p> <form method="POST" action="addItemToList.do">
                <table>
                    <tr>
                        <td>Pizza name <input type="text" name="name"></td>
                        <td>Description <input type="text" name="description"></td>
                        <td>Price <input type="text" name="price"></td>
                    </tr>
                </table>
                <input type="submit" value="Add pizza">
            </form>
            <br><br><br>
            <table border="1" cellpadding="5" cellspacing="10">
                <tr>
                    <th>Delete</th>
                    <th>Name</th>
                    <th>Item</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${pizzaList}" var="pizza">
                    <tr>
                        <td>
                            <form method="POST" action="deleteFromList.do">
                                <input type="hidden" name="pizzaId" value="${pizza.id}">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                        <td>${pizza.name}</td>
                        <td>${pizza.description}</td>
                        <td>${pizza.price}</td>
                    </tr>							
                </c:forEach>
            </table>
<!--            <c:if test="${currentPage != 1}">
                <a href="menu.do?page=${currentPage - 1}">Previous</a>
            </c:if>
            <table border="1" cellpadding="10" cellspacing="20">
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="menu.do?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
            </table>
        <c:if test="${currentPage lt noOfPages}">
            <a href="menu.do?page=${currentPage + 1}">Next</a>
        </c:if>
-->
    </div>
