<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.order_cancellation_lowercase" var="order_cancellation_lowercase"/>
    <fmt:message key="title.order_cancellation" var="order_cancellation"/>
    <fmt:message key="field.order_cancellation" var="order_cancellation_field"/>
    <fmt:message key="title.order_id" var="order_id"/>
    <fmt:message key="title.car_id" var="car_id"/>
    <fmt:message key="field.pick_up_date" var="pick_up_date"/>
    <fmt:message key="field.drop_off_date" var="drop_off_date"/>
    <fmt:message key="title.order_status" var="order_status"/>
    <fmt:message key="field.reason_for_cancelling" var="reason_for_cancelling"/>
    <fmt:message key="message.cancellation_order_failed" var="cancellation_order_failed"/>
    <fmt:message key="field.reason_for_cancelling" var="reason_for_cancelling"/>
    <fmt:message key="message.cancellation_order_failed" var="cancellation_order_failed"/>
    <fmt:message key="message.cancel_order_result" var="cancellation_order_success"/>
    <fmt:message key="button.cancel_order" var="cancel_order_button"/>


    <html>
    <head>
        <title>${order_cancellation_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${order_cancellation}</h4>
    </br>
    <c:forEach var="order" items="${booked_confirmed_orders_session}">
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
    <b>${order_cancellation_field}</b>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="cancelorder"/>
        ${order_id} <input type="text" name="order_id"
                           pattern="\d+" title="<fmt:message key="message.id_validation"/>"
                           value="">
        <br/>
        ${reason_for_cancelling} <input type="text" name="reason_for_canceling" value=""/>
        <br/>
        <input type="submit" value="${cancel_order_button}"/>
    </form>
    <br/>
    <c:if test="${cancel_order_result == false}">
        <strong>${cancellation_order_failed}</strong>
    </c:if>
    <c:if test="${cancel_order_result == true}">
        <strong>${cancellation_order_success}</strong>
    </c:if>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>