<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 22.06.2022
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Current information:
<hr/>
YOUR FIRST NAME: ${first_name_session}
<br/>
YOUR LAST NAME: ${last_name_session}
<br/>
YOUR EMAIL: ${email_session}
<hr/>
Fill in the fields to update user information:
<form action="controller">
    <input type = "hidden" name = "command" value="updateuserpersonalinfo"/>

    Email: <input type = "email" name = "email" value ="">
    <br/>
    First name: <input type = "text" name = "first_name" value ="">
    <br/>
    Last name: <input type = "text" name = "last_name" value ="">
    <br/>
    Confirm password: <input type = "password" name = "password" value ="">
    <br/>
    <input type="submit" name = "sub" value="Update info"/>
    <br/>
</form>
Change personal info result: ${update_personal_info_result}
<br/>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</html>
