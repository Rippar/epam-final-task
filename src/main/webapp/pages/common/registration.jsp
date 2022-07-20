<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 18.06.2022
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.registration_page_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="fragment/welcome_header.jsp"></jsp:include>
    <form action="controller">
        <input type="hidden" name="command" value="registrationuser"/>

        <fmt:message key="field.login"/> <input type="text" name="login"
                                                pattern="[\p{Alpha}][\p{Alpha}\d]{4,29}" title=
                                                    <fmt:message key="message.login_validation"/> value=""/>
        <br/>
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
        <fmt:message key="field.passport_number"/> <input type="text" name="passport"
                                                          pattern="[А-Я]{2}[0-9]{7}"
                                                          title="<fmt:message key="message.passport_validation"/>"
                                                          value="">
        <br/>
        <fmt:message key="field.password"/> <input type="password" name="password"
                                                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}$"
                                                   title="<fmt:message key="message.password_validation"/>" value="">
        <br/>
        <fmt:message key="field.repeat_password"/> <input type="password" name="repeat_password"
                                                          pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}$"
                                                          title="<fmt:message key="message.password_validation"/>"
                                                          value="">
        <br/>
        <input type="submit" name="sub" value="<fmt:message key="button.confirm"/>"/>
        <br/>
        <c:if test="${registration_result == false}">
            <strong><fmt:message key="message.registration_failed"/></strong>
        </c:if>

        <br/>
    </form>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>