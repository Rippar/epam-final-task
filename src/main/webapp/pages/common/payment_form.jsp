<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 28.06.2022
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.payment_page_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.payment_page"/></h4>
    <u><fmt:message key="title.personal_info"/></u>
    </br>
    <fmt:message key="field.first_name"/> ${payment_name_session}
    </br>
    <fmt:message key="field.last_name"/> ${payment_surname_session}
    </br>
    <fmt:message key="field.passport_number"/> ${payment_passport_number_session}
    </br>
    <fmt:message key="field.email"/> ${payment_email_session}
    </br>
    </br>
    <u><fmt:message key="title.car_info"/></u>
    </br>
    <fmt:message key="title.car_brand"/> ${payment_car_brand_session}
    </br>
    <fmt:message key="title.car_model"/> ${payment_car_model_session}
    </br>
    <fmt:message key="title.car_class"/> ${payment_car_class_session}
    </br>
    </br>
    <u><fmt:message key="title.dates_info"/></u>
    </br>
    <fmt:message key="field.pick_up_date"/> ${order_pick_up_date_session}
    </br>
    <fmt:message key="field.drop_off_date"/> ${order_drop_off_date_session}
    </br>
    </br>
    <u> <fmt:message key="title.order_sum"/></u>
        <b>${payment_sum_session}</b>
    <input type="submit" name="sub" value="<fmt:message key="button.payment"/>"/>
    </br>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>