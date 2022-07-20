<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 01.06.2022
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.main_page_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="fragment/welcome_header.jsp"></jsp:include>

    <form action="controller">
        <input type="hidden" name="command" value="gotologinpage"/>
        <input type="submit" value="<fmt:message key="button.login"/>"/>
    </form>
    <form action="controller">
        <input type="hidden" name="command" value="gotoregistrationpage"/>
        <input type="submit" value="<fmt:message key="button.sign_up"/>"/>
    </form>
    <br/>
    <c:if test="${registration_result == true}">
        <strong><fmt:message key="message.registration_success"/></strong>
    </c:if>
    <br/>

    <form action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="button_test"/>
    </form>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>