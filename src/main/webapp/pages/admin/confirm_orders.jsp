<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.confirmation_orders_lowercase" var="confirmation_orders_lowercase"/>
    <fmt:message key="title.confirmation_orders" var="confirmation_orders"/>
    <fmt:message key="title.order_id" var="order_id"/>
    <fmt:message key="title.car_id" var="car_id"/>
    <fmt:message key="field.pick_up_date" var="pick_up_date"/>
    <fmt:message key="field.drop_off_date" var="drop_off_date"/>
    <fmt:message key="title.order_status" var="order_status"/>
    <fmt:message key="field.cancel_order" var="cancel_order"/>
    <fmt:message key="field.confirm_order" var="confirm_order"/>
    <fmt:message key="button.confirm_order" var="confirm"/>
    <fmt:message key="message.confirmation_order_failed" var="failed"/>
    <fmt:message key="message.confirmation_order_success" var="success"/>


    <html>
    <head>
        <title>${confirmation_orders_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${confirmation_orders}</h4>
    <c:forEach var="order" items="${booked_orders_session}">
        <tr>
            <form action="controller">
                <td><c:out value="${order_id} ${order.getOrderId()},"/></td>
                <td><c:out value="${car_id} ${order.getCarId()},"/></td>
                <td><c:out value="${pick_up_date} ${order.getPickUpDate()},"/></td>
                <td><c:out value="${drop_off_date} ${order.getDropOffDate()},"/></td>
                <td><c:out value="${order_status} ${order.getStatus()};"/></td>
                <input type="hidden" name="command" value="confirmorder"/>
                <input type="hidden" name="order_id" value="${order.getOrderId()}">
                <input type="submit" value="${confirm}"/>
            </form>
        </tr>
    </c:forEach>
    <br>
    <br/>

    <c:if test="${confirm_order_result == false}">
        <strong>${failed}</strong>
    </c:if>
    <c:if test="${confirm_order_result == true}">
        <strong>${success}</strong>
    </c:if>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>