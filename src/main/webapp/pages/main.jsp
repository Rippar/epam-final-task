<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 01.06.2022
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<jsp:include page="header.html"></jsp:include>
<p>Welcome to main page!</p>
<form action="controller">
    <input type="hidden" name="command" value="gotoregistrationpage"/>
    <input type="submit" value="Registration"/>
</form>
Registration result: ${registration_result}
<br/>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="gotologinpage"/>
    <input type="submit" value="login"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="Refresh"/>
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
