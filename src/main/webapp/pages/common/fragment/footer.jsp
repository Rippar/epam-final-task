<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 19.06.2022
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head><title>Footer</title></head>
    <body>
    <hr>
    <form action="controller">
        <input type="hidden" name="command" value="changelanguagecommand"/>
        <input type="submit" value="<fmt:message key="footer.locale"/>"/>
    </form>
    <em><fmt:message key="footer.copyright"/></em>
    </body>
    </html>
</fmt:bundle>