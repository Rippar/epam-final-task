<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.fulfillment_orders_lowercase" var="fulfillment_orders_lowercase"/>
    <fmt:message key="title.fulfillment_orders" var="fulfillment_orders"/>
    <fmt:message key="field.fulfill_order" var="fulfill_order"/>
    <fmt:message key="field.damage_description" var="damage_description"/>
    <fmt:message key="title.order_id" var="order_id"/>
    <fmt:message key="title.car_id" var="car_id"/>
    <fmt:message key="field.pick_up_date" var="pick_up_date"/>
    <fmt:message key="field.drop_off_date" var="drop_off_date"/>
    <fmt:message key="title.order_status" var="order_status"/>
    <fmt:message key="field.bill_value" var="bill_value"/>
    <fmt:message key="field.fulfill_order" var="fulfill_order"/>
    <fmt:message key="button.fulfill_order" var="fulfill_order_button"/>
    <fmt:message key="message.fulfillment_order_failed" var="fulfillment_order_failed"/>
    <fmt:message key="message.fulfillment_order_success" var="fulfillment_order_success"/>
    <fmt:message key="message.creation_return_form_failed" var="creation_return_form_failed"/>
    <fmt:message key="message.creation_return_form_success" var="creation_return_form_success"/>
    <fmt:message key="message.cancel_order_result" var="creation_return_form_success"/>
    <fmt:message key="message.price_validation" var = "price_validation"/>

    <html>
    <head>
        <title>${fulfillment_orders_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${fulfillment_orders}</h4>
    </br>
    <c:forEach var="order" items="${confirmed_orders_session}">
        <tr>
            <form method = "POST" action="controller">
                <input type="hidden" name="command" value="completeorder"/>
                <b>
                    <td><c:out value="${order_id} ${order.getOrderId()},"/></td>
                    <td><c:out value="${car_id} ${order.getCarId()},"/></td>
                    <td><c:out value="${pick_up_date} ${order.getPickUpDate()},"/></td>
                    <td><c:out value="${drop_off_date} ${order.getDropOffDate()},"/></td>
                    <td><c:out value="${order_status} ${order.getStatus()};"/></td>
                </b>
                <input type="hidden" name="order_id" value="${order.getOrderId()}">
                <br/>
                ${damage_description}

                <input type="text" name="damage_description" value=""/>
                &nbsp${bill_value}
                <input type="text" name="bill_value" pattern="^\d{0,3}([.]\d{1,2})?$"
                       title="${price_validation}" value=""/>
                <input type="submit" value="${fulfill_order_button}"/>
            </form>
        </tr>
    </c:forEach>
    <br/>
    <br/>
    <c:if test="${complete_order_result == false}">
        <strong>${fulfillment_order_failed}</strong>
    </c:if>
    <c:if test="${complete_order_result == true}">
        <strong>${fulfillment_order_success}</strong>
    </c:if>
    <br/>
    <c:if test="${add_return_form_result == false}">
        <strong>${creation_return_form_failed}</strong>
    </c:if>
    <c:if test="${add_return_form_result == true}">
        <strong>${creation_return_form_success}</strong>
    </c:if>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>