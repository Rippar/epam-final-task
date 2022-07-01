<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 27.06.2022
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
ORDER PAGE
<hr/>
<c:forEach var="car" items="${available_cars_list_session}" >
    <tr>
      <td><c:out value="${ car }" /></td>
      </br>
    </tr>
</c:forEach>

<hr/>
<form action="controller">
    <input type = "hidden" name = "command" value="addorder"/>
    Input the car's id you want to order: <input type = "text" name = "car_id_to_order" value ="">
    <br/>
    <input type="submit" name = "sub" value="Order car"/>
    <br/>
</form>
Order result: ${add_order_result}
<hr/>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="gotohomepage"/>
    <input type="submit" value="Return to home page"/>
</form>
<hr/>
Order data ${order_data_session}
</body>
</html>
