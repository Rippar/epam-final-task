<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 25.06.2022
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.update_cars_page_lowercase" var="update_cars_page_lowercase"/>
    <fmt:message key="title.update_cars_page" var="update_cars_page"/>
    <fmt:message key="title.update_cars_subtitle" var="update_cars_subtitle"/>
    <fmt:message key="title.car_id" var="car_id"/>
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
    <fmt:message key="button.update_car" var = "update_car"/>
    <fmt:message key="message.update_car_failed" var = "update_car_failed"/>
    <fmt:message key="message.update_car_success" var = "update_car_success"/>
    <fmt:message key="title.no_answer" var = "no"/>
    <fmt:message key="title.yes_answer" var = "yes"/>
    <fmt:message key="title.car_conditioner" var = "air_conditioner"/>
    <fmt:message key="title.active" var = "car_active"/>

    <html>
    <head>
        <title>${update_cars_page_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${update_cars_page}</h4>

    <c:forEach var="car" items="${cars_list_to_update}">
        <tr>
            <td><c:out value="${car_id} ${car.getCarId()},"/></td>
            <td><c:out value="${car_brand} ${car.getCarBrand()},"/></td>
            <td><c:out value="${car_model} ${car.getCarModel()},"/></td>
            <td><c:out value="${car_class} ${car.getCarClass()},"/></td>
            <td><c:out value="${car_body} ${car.getCarBody()},"/></td>
            <td> ${car_automatic}
                <c:if test="${car.isAutoTransmission() == false}">
                    ${no},
                </c:if>
                <c:if test="${car.isAutoTransmission() == true}">
                    ${yes},
                </c:if>
            </td>
            <td> ${air_conditioner}
                <c:if test="${car.isAirConditioning() == false}">
                    ${no},
                </c:if>
                <c:if test="${car.isAirConditioning() == true}">
                    ${yes},
                </c:if>
            </td>
            <td><c:out value="${num_of_doors} ${car.getNumOfDoors()},"/></td>
            <td><c:out value="${num_of_seats} ${car.getNumOfSeats()},"/></td>
            <td><c:out value="${rental_price} ${car.getRentalPrice()};"/></td>
            <td> ${car_active}
                <c:if test="${car.isActive() == false}">
                    ${no}
                </c:if>
                <c:if test="${car.isActive() == true}">
                    ${yes}
                </c:if>
            </td>
            </br>
        </tr>
    </c:forEach>
    <br/>

    <b>${update_cars_subtitle}</b>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="updatecar"/>
        ${car_id} <input type="text" name="car_id"
                         pattern="\d+" title="<fmt:message key="message.id_validation"/>"
                         value="">
        <br/>
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
        <br/>
        <input type="submit" name="sub" value="${update_car}"/>
        <br/>
    </form>

    <br/>
    <c:if test="${update_car_result == false}">
        <strong>${update_car_failed}</strong>
    </c:if>
    <c:if test="${update_car_result == true}">
        <strong>${update_car_success}</strong>
    </c:if>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>