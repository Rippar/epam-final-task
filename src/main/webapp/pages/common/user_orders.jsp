<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 29.06.2022
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <fmt:message key="title.id" var = "order_id"/>
    <fmt:message key="title.car_id" var = "car_id"/>
    <fmt:message key="field.pick_up_date" var = "pick_up_date"/>
    <fmt:message key="field.drop_off_date" var = "drop_off_date"/>
    <fmt:message key="title.order_status" var = "order_status"/>
    <fmt:message key="field.cancel_order" var = "cancel_order"/>
    <fmt:message key="button.cancel_order" var = "button_cancel_order"/>
    <fmt:message key="message.incorrect_order_id" var = "incorrect_order_id"/>
    <fmt:message key="message.cancel_order_result" var = "cancel_order_message"/>

    <html>
    <head>
        <title><fmt:message key="title.order_list_page_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.order_list_page"/></h4>
    <c:forEach var="order" items="${user_orders_session}">
        <tr>
            <td><c:out value="${order_id} ${order.getOrderId()},"/></td>
            <td><c:out value="${car_id} ${order.getCarId()},"/></td>
            <td><c:out value="${pick_up_date} ${order.getPickUpDate()},"/></td>
            <td><c:out value="${drop_off_date} ${order.getDropOffDate()},"/></td>
            <td><c:out value="${order_status} ${order.getStatus()};"/></td>
            </br>
        </tr>
    </c:forEach>
    <br/>
    <br/>
    ${cancel_order}
    <form action="controller">
        <input type="hidden" name="command" value="inactivateorder"/>
        <input type="text" name="order_id" value=""/>
        <input type="submit" value="${button_cancel_order}"/>
    </form>
    <c:if test="${cancel_order_result == false}">
        <strong>${incorrect_order_id}</strong>
    </c:if>
    <c:if test="${cancel_order_result == true}">
        <strong>${cancel_order_message}</strong>
    </c:if>
    <br/>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>
