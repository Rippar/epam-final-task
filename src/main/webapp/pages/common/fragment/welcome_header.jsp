<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 17.07.2022
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title>Welcome header</title>
    </head>
    <body>
    <nav>
        <a href="<c:url value = "controller?command=gotomainpage"/>"><fmt:message key="header.mainpage"/></a> |
        <a href="<c:url value = "#"/>"><fmt:message key="header.contacts"/></a> | <a
            href="<c:url value = "#"/>"><fmt:message key="header.about"/></a>
    </nav>
    <h2><fmt:message key="title.header"/></h2>
    <hr>
    </body>
    </html>
</fmt:bundle>
