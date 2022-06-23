<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 23.06.2022
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<hr/>
Fill in the field to inactivate user:
<form action="controller">
    <input type = "hidden" name = "command" value="inactivateuser"/>
    User's id to inactivate: <input type = "text" name = "user_id_to_inactivate" value ="">
    <br/>
    <input type="submit" name = "sub" value="Inactivate user"/>
    <br/>
</form>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
<hr/>
User data ${user_data_session}
<br/>
Result: ${inactivate_user_result}
<br/>
</body>
</html>
