<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 26.06.2022
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<hr/>
Fill in the field to inactivate car:
<form action="controller">
    <input type = "hidden" name = "command" value="inactivatecar"/>
    Car's id to inactivate: <input type = "text" name = "car_id_to_inactivate" value ="">
    <br/>
    <input type="submit" name = "sub" value="Inactivate car"/>
    <br/>
</form>
Car data ${car_data_session}
<br/>
Result: ${inactivate_car_result}
<br/>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Return to account's page"/>
</form>
</body>
</html>
