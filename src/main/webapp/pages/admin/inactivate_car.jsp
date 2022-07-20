<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 26.06.2022
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.id" var = "car_id"/>
    <fmt:message key="title.car_brand" var = "car_brand"/>
    <fmt:message key="title.car_model" var = "car_model"/>
    <fmt:message key="title.car_class" var = "car_class"/>
    <fmt:message key="title.car_body" var = "car_body"/>
    <fmt:message key="title.car_body" var = "car_body"/>
    <fmt:message key="title.car_automatic" var = "car_automatic"/>
    <fmt:message key="title.no_answer" var = "no"/>
    <fmt:message key="title.yes_answer" var = "yes"/>
    <fmt:message key="title.car_conditioner" var = "air_conditioner"/>
    <fmt:message key="title.num_of_doors" var = "num_of_doors"/>
    <fmt:message key="title.num_of_seats" var = "num_of_seats"/>
    <fmt:message key="title.rental_price" var = "rental_price"/>
    <fmt:message key="title.active" var = "car_active"/>

    <html>
    <head>
        <title><fmt:message key="title.inactivate_car_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.inactivate_car"/></h4>

    <c:forEach var="car" items="${cars_list_to_inactivate}">
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
    </br>
    <form action="controller">
        <input type="hidden" name="command" value="inactivatecar"/>
        <fmt:message key="field.inactivate_car_id"/> <input type="text" name="car_id_to_inactivate"
                                                            pattern="\d+" title="<fmt:message key="message.id_validation"/>"
                                                            value="">
        <br/>
        <input type="submit" name="sub" value="<fmt:message key="button.inactivate_car"/>"/>
        <br/>
    </form>
    <br/>
    <c:if test="${inactivate_car_result == false}">
        <strong><fmt:message key="message.inactivate_car_failed"/></strong>
    </c:if>
    <c:if test="${inactivate_car_result == true}">
        <strong><fmt:message key="message.inactivate_car_success"/></strong>
    </c:if>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>