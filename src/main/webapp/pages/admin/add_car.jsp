<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 24.06.2022
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<hr/>
Fill in the fields to add new car:
<form action="controller">
    <input type = "hidden" name = "command" value="addcar"/>

    CAR BRAND: <input type = "text" name = "car_brand" value ="">
    <br/>
    CAR MODEL: <input type = "text" name = "car_model" value ="">
    <br/>
    CAR CLASS: <input type = "text" name = "car_class" value ="">
    <br/>
    CAR BODY: <input type = "text" name = "car_body" value ="">
    <br/>
    AUTO TRANSMISSION (1/0): <input type = "text" name = "auto_transmission" value ="">
    <br/>
    AIR CONDITIONING (1/0): <input type = "text" name = "air_conditioning" value ="">
    <br/>
    NUM OF DOORS: <input type = "text" name = "num_of_doors" value ="">
    <br/>
    NUM OF SEATS: <input type = "text" name = "num_of_seats" value ="">
    <br/>
    RENTAL PRICE: <input type = "text" name = "rental_price" value ="">
    <br/>
    <input type="submit" name = "sub" value=Add car/>
    <br/>
</form>
<hr/>
Car data ${car_data_session}
<br/>
Result: ${add_car_result}
<br/>
</body>
</html>
