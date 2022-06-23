<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 13.06.2022
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="controller">
    <!-- отображение поля меняется в зависимости от команды?-->
    <input type = "hidden" name = "command" value="login"/>
    <!--в будущем сделать валидацию на то, чтобы нельзя было ввести не число (валидация на клиенте)-->
    <!-- валидация на сервере будет в слое Service)-->
    Login: <input type="text" name = "login" value=""/>
    <br/>
    Password: <input type = "password" name = "password" value ="">
    <br/>
    <input type="submit" name = "sub" value="Push"/>
    <br/>
    ${login_msg}
    <br/>
    ${user_data_session}

</form>
</body>
</html>
