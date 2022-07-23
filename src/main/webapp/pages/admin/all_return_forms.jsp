<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 01.07.2022
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.list_all_return_forms_lowercase" var = "list_all_return_forms_lowercase"/>
    <fmt:message key="title.list_all_return_forms" var = "list_all_return_forms"/>
    <fmt:message key="title.id" var = "return_form_id"/>
    <fmt:message key="title.order_id" var = "order_id"/>
    <fmt:message key="title.return_date" var = "return_date"/>
    <fmt:message key="title.damage_description" var = "damage_description"/>
    <fmt:message key="title.bill_value" var = "bill_value"/>


    <html>
    <head>
        <title>${list_all_return_forms_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${list_all_return_forms}</h4>
    <c:forEach var="returnForm" items="${return_form_list}">
        <tr>
            <td><c:out value="${return_form_id} ${returnForm.getReturnFormId()},"/></td>
            <td><c:out value="${order_id} ${returnForm.getOrderId()},"/></td>
            <td><c:out value="${return_date} ${returnForm.getDateOfReturn()},"/></td>
            <td><c:out value="${damage_description} ${returnForm.getDamageDescription()},"/></td>
            <td><c:out value="${bill_value} ${returnForm.getBillValue()};"/></td>
            </br>
        </tr>
    </c:forEach>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>