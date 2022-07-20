<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 16.07.2022
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title>Header</title>
    </head>
    <body>
    <fmt:message key="header.welcome"/>, ${login_session}
    <br/>
    <span style="float:left;">
    <form action="controller">
        <input type="hidden" name="command" value="gotoaccountpage"/>
        <input type="submit" value="<fmt:message key="button.profile"/>"/>
    </form>
</span>
    <span style="float:left;">
    <form action="controller">
        <input type="hidden" name="command" value="logout"/>
        <input type="submit" value="<fmt:message key="button.logout"/>"/>
    </form>
</span>
    <br/>
    <br/>
    <br/>
    <span style="float:left;">
    <nav>
        <a href="<c:url value = "controller?command=gotohomepage"/>"><fmt:message key="header.homepage"/></a> | <a
            href="<c:url value = "#"/>"><fmt:message key="header.contacts"/></a> | <a
            href="<c:url value = "#"/>"><fmt:message key="header.about"/></a>
    </nav>
</span>
    <h2>
        <fmt:message key="header.headline"/>
    </h2>
    <hr>
    </body>
    </html>
</fmt:bundle>