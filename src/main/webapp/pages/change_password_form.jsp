<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 22.06.2022
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller">
    <input type = "hidden" name = "command" value="changepassword"/>
    Old password: <input type = "password" name = "password" value ="">
    <br/>
    New password: <input type = "password" name = "new_password" value ="">
    <br/>
    Repeat new password: <input type = "password" name = "repeat_new_password" value ="">
    <input type="submit" name = "sub" value="Update password"/>
    <br/>
</form>
<hr/>
User data ${user_data_session}
<br/>
Result: ${change_password_result}
<br/>
If result = false, please return to the account, refresh the page and try again
</body>
</html>
