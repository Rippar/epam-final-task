<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 01.07.2022
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Completed orders:
</br>
<c:forEach var="order" items="${completed_orders_session}">
    <tr>
        <td><c:out value="${ order }"/></td>
        </br>
    </tr>
</c:forEach>
<hr>
Please insert the id of the order to make the return form to it:
<br/>
<form action="controller">
    <input type="hidden" name="command" value="----------"/>
    Order's id: <input type="text" name="order_id" value=""/>
    <br/>
    Damage description (if necessary): <input type="text" name="damage_description" value=""/>
    </br>
    Additional bill amount (if necessary): <input type="text" pattern="^\d+([.]\d{1,2})?$" name="bill_value" value=""/>
    </br>
    <input type="submit" value="Create the return form"/>
</form>
<br/>
Return form's creation result: ${return_form_creation_result}
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</html>
