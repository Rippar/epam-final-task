<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Confirmed orders:
</br>
<c:forEach var="order" items="${confirmed_orders_session}">
    <tr>
        <td><c:out value="${ order }"/></td>
        </br>
    </tr>
</c:forEach>
<hr>
Please insert the id of the order to make it complete:
<br/>
<form action="controller">
    <input type="hidden" name="command" value="completeorder"/>
    Order's id: <input type="text" name="order_id" value=""/>
    <br/>
    Damage description (if necessary): <input type="text" name="damage_description" value=""/>
    </br>
    Additional bill amount (if necessary): <input type="text" name="bill_value" pattern="^\d+([.]\d{1,2})?$"
                                                  title="Bill value must be a number" value=""/>
    </br>
    <input type="submit" value="make the order complete"/>
</form>
<br/>
Complete order result: ${complete_order_result}
<br/>
Create return form result: ${add_return_form_result}
<br/>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</body>
</html>
