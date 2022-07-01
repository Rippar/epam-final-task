<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 01.07.2022
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Return forms</title>
</head>
<body>
All return forms:
</br>
<c:forEach var="returnForm" items="${return_form_list}" >
    <tr>
        <td><c:out value="${ returnForm }" /></td>
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
