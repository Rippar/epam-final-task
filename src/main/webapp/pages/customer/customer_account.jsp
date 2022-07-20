<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 20.06.2022
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.client_personal_account_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.client_personal_account"/></h4>

    <form action="controller">
        <input type="hidden" name="command" value="gotochangepassword"/>
        <input type="submit" value="<fmt:message key="button.changing_password"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotochangepersonalinfo"/>
        <input type="submit" value="<fmt:message key="button.changing_personal_info"/>"/>
    </form>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>