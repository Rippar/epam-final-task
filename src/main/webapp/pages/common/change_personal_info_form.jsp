<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 22.06.2022
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.change_personal_info_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.change_personal_info"/></h4>
    <b><fmt:message key="title.current_personal_info"/></b>
    <br/>
    <fmt:message key="field.first_name"/> ${first_name_session}
    <br/>
    <fmt:message key="field.last_name"/> ${last_name_session}
    <br/>
    <fmt:message key="field.email"/> ${email_session}
    <br/>
    <br/>
    <b><fmt:message key="title.update_personal_info"/></b>
    <form action="controller">
        <input type="hidden" name="command" value="updateuserpersonalinfo"/>

        <fmt:message key="field.email"/> <input type="email" name="email"
                                                pattern="(([\p{Alpha}\d._]+){5,25}@([\p{Lower}]+){3,7}\.([\p{Lower}]+){2,3})"
                                                title="<fmt:message key="message.email_validation"/>" value="">
        <br/>
        <fmt:message key="field.first_name"/> <input type="text" name="first_name"
                                                     pattern="[А-Я\p{Upper}][а-яё\p{Lower}]{1,15}"
                                                     title="<fmt:message key="message.firstname_validation"/>" value="">
        <br/>
        <fmt:message key="field.last_name"/> <input type="text" name="last_name"
                                                    pattern="[А-Я\p{Upper}][а-я\p{Lower}]{1,20}"
                                                    title="<fmt:message key="message.lastname_validation"/>" value="">
        <br/>
        <fmt:message key="field.confirm_password"/> <input type="password" name="password" value="">
        <br/>
        <input type="submit" name="sub" value="<fmt:message key="button.change_personal_info"/>"/>
        <br/>
    </form>
    <c:if test="${update_personal_info_result == false}">
        <strong><fmt:message key="message.change_personal_info_failed"/></strong>
    </c:if>
    <c:if test="${update_personal_info_result == true}">
        <strong><fmt:message key="message.change_personal_info_success"/></strong>
    </c:if>
    <br/>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>
