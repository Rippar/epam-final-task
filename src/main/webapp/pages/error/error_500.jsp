<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 02.06.2022
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
Request from: ${pageContext.errorData.requestURI} isdailed <br/>
Servlet name: ${pageContext.errorData.servletName} <br/>
Status code: ${pageContent.errorData.statusCode} <br/>
Exception: ${pageContext.exception} <br/>
Exception: ${pageContext.exception.message} <br/>
<br/><br/><br/>
Message from exception: ${error_msg}
</body>
</html>
