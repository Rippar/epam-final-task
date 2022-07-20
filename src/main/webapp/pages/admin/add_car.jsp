<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 24.06.2022
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.add_cars_page_lowercase" var = "add_cars_page_lowercase"/>
    <fmt:message key="title.add_cars_page" var = "add_cars_page"/>
    <fmt:message key="title.add_cars_subtitle" var = "add_cars_subtitle"/>
    <fmt:message key="title.car_brand" var = "car_brand"/>
    <fmt:message key="title.car_model" var = "car_model"/>
    <fmt:message key="title.car_class" var = "car_class"/>
    <fmt:message key="title.car_body" var = "car_body"/>
    <fmt:message key="title.car_automatic" var = "car_automatic"/>
    <fmt:message key="title.car_conditioner" var = "car_conditioner"/>
    <fmt:message key="title.num_of_doors" var = "num_of_doors"/>
    <fmt:message key="title.num_of_seats" var = "num_of_seats"/>
    <fmt:message key="title.rental_price" var = "rental_price"/>
    <fmt:message key="message.car_brand_validation" var = "car_brand_validation"/>
    <fmt:message key="message.car_model_validation" var = "car_model_validation"/>
    <fmt:message key="message.car_class_validation" var = "car_class_validation"/>
    <fmt:message key="message.car_body_validation" var = "car_body_validation"/>
    <fmt:message key="message.boolean_validation" var = "boolean_validation"/>
    <fmt:message key="message.car_num_of_doors_validation" var = "car_num_of_doors_validation"/>
    <fmt:message key="message.car_num_of_seats_validation" var = "car_num_of_seats_validation"/>
    <fmt:message key="message.price_validation" var = "price_validation"/>
    <fmt:message key="message.price_validation" var = "price_validation"/>
    <fmt:message key="button.add_car" var = "add_car"/>
    <fmt:message key="message.add_car_failed" var = "add_car_failed"/>
    <fmt:message key="message.add_car_success" var = "add_car_success"/>

    <html>
    <head>
        <title>${add_cars_page_lowercase}</title>
    </head>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${add_cars_page}</h4>

    <b>${add_cars_subtitle}</b>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="addcar"/>

        ${car_brand} <input type="text" name="car_brand"
                            pattern="[а-яё\\p{Lower}]{1,15}" title=
                                "${car_brand_validation}" value=""/>
        <br/>
        ${car_model} <input type="text" name="car_model"
                            pattern="^[\w\sА-я.\-]{1,20}$" title=
                                "${car_model_validation}" value=""/>
        <br/>
        ${car_class} <input type="text" name="car_class"
                            pattern="премиум|стандарт|бизнес|компакт|эконом" title=
                                "${car_class_validation}" value=""/>
        <br/>
        ${car_body} <input type="text" name="car_body"
                           pattern="седан|универсал|кроссовер|минивэн|хэтчбек|купе|кабриолет" title=
                               "${car_body_validation}" value=""/>
        <br/>
        ${car_automatic} <input type="text" name="auto_transmission"
                                pattern="[01]" title=
                                    "${boolean_validation}" value=""/>
        <br/>
        ${car_conditioner}  <input type="text" name="air_conditioning"
                                   pattern="[01]" title=
                                       "${boolean_validation}" value=""/>
        <br/>
        ${num_of_doors} <input type="text" name="num_of_doors"
                               pattern="[2345]" title=
                                       "${car_num_of_doors_validation}" value=""/>
        <br/>
        ${num_of_seats} <input type="text" name="num_of_seats"
                               pattern="[245678]" title=
                                       "${car_num_of_seats_validation}" value=""/>
        <br/>
        ${rental_price} <input type="text" name="rental_price"
                               pattern="^\d{0,3}([.]\d{1,2})?$" title=
                                       "${price_validation}" value=""/>
        <br/>
        <input type="submit" name="sub" value="${add_car}"/>
        <br/>
    </form>

    <br/>
    <c:if test="${add_car_result == false}">
        <strong>${add_car_failed}</strong>
    </c:if>
    <c:if test="${add_car_result == true}">
        <strong>${add_car_success}</strong>
    </c:if>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>