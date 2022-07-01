<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
All orders:
</br>
<c:forEach var="order" items="${orders_list}" >
    <tr>
        <td><c:out value="${ order }" /></td>
        </br>
    </tr>
</c:forEach>
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotoconfirmorderspage"/>
    <input type="submit" value="Go to confirm orders"/>
</form>
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotocompleteorderspage"/>
    <input type="submit" value="Go to make orders complete"/>
</form>
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotocancelorderspage"/>
    <input type="submit" value="Go to cancel orders"/>
</form>
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</html>
