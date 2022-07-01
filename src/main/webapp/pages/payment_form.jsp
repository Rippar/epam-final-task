<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 28.06.2022
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
Payment page
<hr>
Personal info:
</br>
Your name: ${payment_name_session}
</br>
Your surname: ${payment_surname_session}
</br>
Your passport: ${payment_passport_number_session}
</br>
Your email: ${payment_email_session}
</br>
<hr>
Car's info:
</br>
Car brand: ${payment_car_brand_session}
</br>
Car model: ${payment_car_model_session}
</br>
Car class: ${payment_car_class_session}
<hr>
Dates info:
</br>
Pick up date: ${order_pick_up_date_session}
</br>
Drop off date: ${order_drop_off_date_session}
</br>
<hr>
ORDER SUM:
${payment_sum_session}
</br>
<input type="submit" name = "sub" value="Pay"/>
<hr>
</br>
<form action="controller">
    <input type="hidden" name="command" value="gotohomepage"/>
    <input type="submit" value="Return to home page"/>
</form>
</body>
</html>
