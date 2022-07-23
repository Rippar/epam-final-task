<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 27.06.2022
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.order_page_lowercase" var = "order_page"/>
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


    <!DOCTYPE html>
    <html>
    <head>
        <title>${order_page}</title>
    </head>
    <body>
    <jsp:include page="fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.order_page"/></h4>
    <c:forEach var="car" items="${available_cars_list_session}">
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

            </br>
        </tr>
    </c:forEach>
    <br/>
    <form method = "POST" action="controller">
        <input type="hidden" name="command" value="addorder"/>
        <fmt:message key="field.order_id"/> <input type="text" name="car_id_to_order" value="">
        <br/>
        <input type="submit" name="sub" value="<fmt:message key="button.order"/>"/>
        <br/>
    </form>
    <c:if test="${add_order_result == false}">
        <strong><fmt:message key="message.incorrect_car_id"/></strong>
    </c:if>
    <br/>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>