<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 22.06.2022
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.change_password_page_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.change_password_page"/></h4>
    <form action="controller">
        <input type="hidden" name="command" value="changepassword"/>
        <fmt:message key="field.old_password"/> <input type="password" name="password" value="">
        <br/>
        <fmt:message key="field.new_password"/> <input type="password" name="new_password" value="">
        <br/>
        <fmt:message key="field.repeat_password"/> <input type="password" name="repeat_new_password"
                                                          pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,20}$"
                                                          title="<fmt:message key="message.password_validation"/>" value="">
        <input type="submit" name="sub" value="<fmt:message key="button.change_password"/>"/>
        <br/>
    </form>
    <c:if test="${change_password_result == false}">
        <strong><fmt:message key="message.change_password_failed"/></strong>
    </c:if>
    <c:if test="${change_password_result == true}">
        <strong><fmt:message key="message.change_password_success"/></strong>
    </c:if>
    <br/>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>