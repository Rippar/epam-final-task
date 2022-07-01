<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 29.06.2022
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
ORDER'S PAGE
<br/>
My current orders:
<hr/>
<c:forEach var="order" items="${user_orders_session}" >
    <tr>
        <td><c:out value="${ order }" /></td>
        </br>
    </tr>
</c:forEach>
<br/>
Please insert the id of your order to cancel it:
<br/>
<form action="controller">
    <input type="hidden" name="command" value="inactivateorder"/>
    <input type="text" name = "order_id" value=""/>
    <input type="submit" value="Cancel the order"/>
</form>
<br/>
Cancel order result: ${cancel_order_result}
<hr/>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="gotohomepage"/>
    <input type="submit" value="Return to home page"/>
</form>
</body>
</html>
