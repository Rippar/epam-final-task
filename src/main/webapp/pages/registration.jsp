<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 18.06.2022
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="controller">
    <input type = "hidden" name = "command" value="registrationuser"/>

    Login: <input type="text" name = "login" value=""/>
    <br/>
    Email: <input type = "email" name = "email" value ="">
    <br/>
    First name: <input type = "text" name = "first_name" value ="">
    <br/>
    Last name: <input type = "text" name = "last_name" value ="">
    <br/>
    Passport number: <input type = "text" name = "passport" value ="">
    <br/>
    Password: <input type = "password" name = "password" value ="">
    <br/>
    Repeat password: <input type = "password" name = "repeat_password" value ="">
    <br/>
    <input type="submit" name = "sub" value="Push"/>
    <br/>
    ${login_msg}
    <br/>
    Registration result:     ${registration_result}
    <br/>
    ${user_data_session}


</form>
</body>
</html>
