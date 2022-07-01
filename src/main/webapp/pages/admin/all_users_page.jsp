<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.06.2022
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
All users:
</br>
<c:forEach var="user" items="${users_list}" >
    <tr>
        <td><c:out value="${ user }" /></td>
        </br>
    </tr>
</c:forEach>
<hr>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</html>
