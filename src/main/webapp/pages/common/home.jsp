<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 20.06.2022
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.home_page_lowercase"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>jQuery UI Datepicker - Default functionality</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#pickupdate").datepicker();
                $("#dropoffdate").datepicker();
            });
        </script>
    </head>
    <body>
    <jsp:include page="fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.homepage"/></h4>
    <strong><fmt:message key="title.select_date"/></strong>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="gotoorderform"/>
        <p><fmt:message key="field.pick_up_date"/> <input type="text" name="pick_up_date" id="pickupdate"></p>
        <p><fmt:message key="field.drop_off_date"/> <input type="text" name="drop_off_date" id="dropoffdate"></p>
        <input type="submit" value="<fmt:message key="button.show_offers"/>"/>
    </form>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="gotouserorderspage"/>
        <input type="submit" value="<fmt:message key="button.my_orders"/>"/>
    </form>
    <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>