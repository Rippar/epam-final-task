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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jQuery UI Datepicker - Default functionality</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#pickupdate" ).datepicker();
            $( "#dropoffdate" ).datepicker();
        } );
    </script>
</head>
<body>
HOME PAGE
<hr/>
Hello, ${login_session}
<br/>
<hr/>
<strong>Please choose the dates to show you offers:</strong>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="gotoorderform"/>
    <p>Pick up date: <input type="text" name = "pick_up_date" id="pickupdate"></p>
    <p>Drop off date: <input type="text" name = "drop_off_date" id="dropoffdate"></p>
    <input type="submit" value="Show offers"/>
</form>
<br/>
Order data ${order_data_session}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotouserorderspage"/>
    <input type="submit" value="Go to my orders"/>
</form>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="gotoaccountpage"/>
    <input type="submit" value="Go to profile"/>
</form>
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logOut"/>
</form>
<hr/>
User's id ${user_id_session}
<br/>
<br/>
</body>
</html>
