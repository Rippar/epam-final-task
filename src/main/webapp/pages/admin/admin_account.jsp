<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 20.06.2022
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language_session}" scope="session"/>
<fmt:bundle basename="properties.pagecontent">
    <html>
    <head>
        <title><fmt:message key="title.admin_personal_account_lowercase"/></title>
    </head>
    <body>
    <jsp:include page="../common/fragment/header.jsp"></jsp:include>
    <h4><fmt:message key="title.admin_personal_account"/></h4>
    <u><fmt:message key="title.changing_personal_info"/></u>
    <br/>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="gotochangepassword"/>
        <input type="submit" value="<fmt:message key="button.changing_password"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotochangepersonalinfo"/>
        <input type="submit" value="<fmt:message key="button.changing_personal_info"/>"/>
    </form>
    <br/>
    <u><fmt:message key="title.admin_functions"/></u>
    <br/>
    <br/>
    <form action="controller">
        <input type="hidden" name="command" value="findallusers"/>
        <input type="submit" value="<fmt:message key="button.list_all_users"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="findallcars"/>
        <input type="submit" value="<fmt:message key="button.list_all_cars"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="findallorders"/>
        <input type="submit" value="<fmt:message key="button.list_all_orders"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="findallreturnforms"/>
        <input type="submit" value="<fmt:message key="button.list_all_return_forms"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotoaddcarpage"/>
        <input type="submit" value="<fmt:message key="button.add_car"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotoupdatecarpage"/>
        <input type="submit" value="<fmt:message key="button.update_car"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotoinactivateuserpage"/>
        <input type="submit" value="<fmt:message key="button.inactivate_user"/>"/>
    </form>

    <form action="controller">
        <input type="hidden" name="command" value="gotoinactivatecarpage"/>
        <input type="submit" value="<fmt:message key="button.inactivate_car"/>"/>
    </form>
    <br/>
    <jsp:include page="../common/fragment/footer.jsp"></jsp:include>
    </body>
    </html>
</fmt:bundle>