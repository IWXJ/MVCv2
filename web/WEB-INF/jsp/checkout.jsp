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
        <p>
        <p>
            <table border="1" cellpadding="10" cellspacing="20">
                <tr>
                    <th>Quantity</th>
                    <th>Item</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${orderList.orderedProductList}" var="item">
                        <tr><td>${item.quantity}</td>
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
        <p>
            Do you wish to have your pizzas delivered at an alternative address?
            <input type="checkbox" name="alternativeAddressYes" id="alternativeAddressYes" onclick="return showAlternativeDeliveryAddress()"/>
            <div id="alternativeDeliveryAddress">
                
            </div>
            <div id="resultAlternativeDeliveryAddress">
                
            </div>
        <script src="../../jquery-1.11.0.js" type="text/javascript"></script>
            <script type="text/javascript">
//                var form = $('#alternativeDeliveryAddress');
//                $( "#submitFormAlternativeDeliveryAddress" ).click(function(){
//                    $.ajax({
//                    type: form.attr('method'),
//                    url: form.attr('action'),
//                    data: value,
//                    success: function (data) {
//                    var result=data;
////                    $('#resultAlternativeDeliveryAddress').attr("value",result);
//                    $('#resultAlternativeDeliveryAddress').html(result);
//                    }
//                    });
//                    return false;
//                });
                function processAlternativeDeliveryAddress() { 
                        $.ajax( {
                            type: 'POST',
                            url: 'alternativeDeliveryAddress.do?alternativeAddressStreet=' 
                                + encodeURIComponent(document.getElementById('alternativeAddressStreet').value) + 'alternativeAddressZipcode=' 
                                + encodeURIComponent(document.getElementById('alternativeAddressZipcode').value) + 'alternativeAddressCity=' 
                                + encodeURIComponent(document.getElementById('alternativeAddressCity').value),
                            success: function(result) {
//                                $('#resultAlternativeDeliveryAddress').attr("value",result);
//                                $('#resultAlternativeDeliveryAddress').html(data);
                                $('#resultAlternativeDeliveryAddress').append(result);
                            }
                        } );
                    return false;
                }
            </script>
        <p>
            Thank you for shopping with us!
        </p>
    </div>
