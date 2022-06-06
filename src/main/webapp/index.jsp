<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<br/>
<form action="controller">
    <!-- отображение поля меняется в зависимости от команды?-->
    <input type = "hidden" name = "command" value="login"/>
    <!--в будущем сделать валидацию на то, чтобы нельзя было ввести не число (валидация на клиенте)-->
    <!-- валидация на сервере будет в слое Service)-->
    Login: <input type="text" name = "login" value=""/>
    <br/>
    Password: <input type = "password" name = "pass" value ="">
    <br/>
    <input type="submit" name = "sub" value="Push"/>
    <br/>
    ${login_msg}
</form>

</body>
</html>