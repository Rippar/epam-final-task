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
Hello (forward) = ${user}
<hr/>
Hi (redirect/forward) = ${user_name}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logOut"/>
</form>
</body>
</html>
