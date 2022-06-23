<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 20.06.2022
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
HOME PAGE
<hr/>
Hello, ${login_session}
<br/>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Go to profile"/>
</form>
<hr/>

User data ${user_data_session}
<br/>
</body>
</html>
