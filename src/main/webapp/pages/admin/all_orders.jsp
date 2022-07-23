<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 30.06.2022
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.list_all_orders_lowercase" var = "list_all_orders_lowercase"/>
    <fmt:message key="title.list_all_orders" var = "list_all_orders"/>
    <fmt:message key="title.id" var = "order_id"/>
    <fmt:message key="title.car_id" var = "car_id"/>
    <fmt:message key="field.pick_up_date" var = "pick_up_date"/>
    <fmt:message key="field.drop_off_date" var = "drop_off_date"/>
    <fmt:message key="title.order_status" var = "order_status"/>
    <fmt:message key="field.cancel_order" var = "cancel_order"/>
    <fmt:message key="button.go_to_booked_orders" var = "go_to_booked_orders"/>
    <fmt:message key="button.go_to_confirmed_orders" var = "go_to_confirmed_orders"/>
    <fmt:message key="button.go_to_cancel_order_page" var = "go_to_cancel_order_page"/>

    <html>
    <head>
        <title>${list_all_orders_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${list_all_orders}</h4>
    <c:forEach var="order" items="${orders_list}">
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
    <form action="controller">
        <input type="hidden" name="command" value="gotoconfirmorderspage"/>
        <input type="submit" value="${go_to_booked_orders}"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotocompleteorderspage"/>
        <input type="submit" value="${go_to_confirmed_orders}"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotocancelorderspage"/>
        <input type="submit" value="${go_to_cancel_order_page}"/>
    </form>

    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>