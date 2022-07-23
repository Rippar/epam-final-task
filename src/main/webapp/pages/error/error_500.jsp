<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 02.06.2022
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title>500</title>
    </head>
    <body>
    <nav>
        <a href="<c:url value = "controller?command=logout"/>"><fmt:message key="header.mainpage"/></a>
    </nav>
    <h2><fmt:message key="title.error_page"/> 500</h2>
    <h4><fmt:message key="message.500_message"/></h4>
    <br/>
    <br/>
    <hr/>

    </body>
    </html>
</fmt:bundle>