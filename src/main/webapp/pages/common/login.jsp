<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 13.06.2022
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
<html>
<head>
    <title><fmt:message key="title.login_page_lowercase"/></title>
</head>
<body>
<jsp:include page="fragment/welcome_header.jsp"></jsp:include>
<form method = "POST" action="controller">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="field.login"/> <input type="text" name="login" value=""/>
    <br/>
    <fmt:message key="field.password"/> <input type="password" name="password" value="">
    <br/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="button.confirm"/>"/>
    <br/>
    <c:if test="${login_result == false}">
        <strong><fmt:message key="message.login_failed"/></strong>
    </c:if>

    <br/>
</form>
<jsp:include page="fragment/footer.jsp"></jsp:include>
</body>
</html>
</fmt:bundle>