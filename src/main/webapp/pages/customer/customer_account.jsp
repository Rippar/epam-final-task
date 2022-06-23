<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 20.06.2022
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
CUSTOMER'S ACCOUNT PAGE
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotochangepassword"/>
    <input type="submit" value="Change password"/>
</form>
<hr/>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotochangepersonalinfo"/>
    <input type="submit" value="Change personal info"/>
</form>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotohomepage"/>
    <input type="submit" value="Return to home page"/>
</form>
<hr/>
User data ${user_data_session}

</body>
</html>