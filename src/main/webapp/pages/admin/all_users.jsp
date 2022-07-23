<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.06.2022
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">

    <fmt:message key="title.list_all_users_lowercase" var = "list_all_users_lowercase"/>
    <fmt:message key="title.list_all_users" var = "list_all_users"/>
    <fmt:message key="title.id" var = "user_id"/>
    <fmt:message key="field.login" var = "login"/>
    <fmt:message key="field.first_name" var = "first_name"/>
    <fmt:message key="field.last_name" var = "last_name"/>
    <fmt:message key="field.email" var = "email"/>
    <fmt:message key="field.passport_number" var = "passport"/>
    <fmt:message key="field.passport_number" var = "passport"/>
    <fmt:message key="title.active" var = "user_active"/>
    <fmt:message key="title.no_answer" var = "no"/>
    <fmt:message key="title.yes_answer" var = "yes"/>

    <html>
    <head>
        <title>${list_all_users_lowercase}</title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4>${list_all_users}</h4>
    <c:forEach var="user" items="${users_list}">
        <tr>
            <td><c:out value="${user_id} ${user.getUserId()},"/></td>
            <td><c:out value="${login} ${user.getLogin()},"/></td>
            <td><c:out value="${first_name} ${user.getFirstName()},"/></td>
            <td><c:out value="${last_name} ${user.getLastName()},"/></td>
            <td><c:out value="${email} ${user.getEmail()},"/></td>
            <td><c:out value="${passport} ${user.getPassportNum()},"/></td>

            <td> ${user_active}
                <c:if test="${user.isActive() == false}">
                    ${no}
                </c:if>
                <c:if test="${user.isActive() == true}">
                    ${yes}
                </c:if>
            </td>
            </br>
        </tr>
    </c:forEach>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>