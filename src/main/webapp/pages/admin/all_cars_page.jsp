<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 26.06.2022
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
All cars:
</br>
<c:forEach var="car" items="${cars_list}" >
    <tr>
        <td><c:out value="${ car }" /></td>
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
