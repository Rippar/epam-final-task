<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 02.06.2022
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title>404</title>
    </head>
    <jsp:include page="../common/fragment/error_header.jsp"></jsp:include>
    <body>
    <h4><fmt:message key="message.404_message"/></h4>
    <br/>
    <br/>
    <hr/>
    </body>
    </html>
</fmt:bundle>
