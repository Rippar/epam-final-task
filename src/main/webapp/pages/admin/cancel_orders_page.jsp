<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Booked and confirmed orders:
</br>
<c:forEach var="order" items="${booked_confirmed_orders_session}">
    <tr>
        <td><c:out value="${ order }"/></td>
        </br>
    </tr>
</c:forEach>
<hr>
Please insert the id of the order to cancel it:
<br/>
<form action="controller">
    <input type="hidden" name="command" value="cancelorder"/>
    Order's id: <input type="text" name="order_id" value=""/>
    <br/>
    The reason for canceling: <input type="text" name="reason_for_canceling" value=""/>
    <input type="submit" value="cancel the order"/>
</form>
<br/>
Cancel order result: ${cancel_order_result}
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</html>
