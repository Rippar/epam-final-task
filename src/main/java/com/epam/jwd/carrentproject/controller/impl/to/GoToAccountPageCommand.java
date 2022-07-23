package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.entity.UserRole.ADMIN_ROLE;

/**
 * The {@code GoToAccountPageCommand} class implements the functional of {@link Command}
 * The class executes the command to go to the account page
 *
 * @author Dmitry Murzo
 */
public class GoToAccountPageCommand implements Command {

    /**
     * The method executes the command to go to the account page depending on the user's role, writes an additional
     * info to the user's data and session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();

        session.setAttribute(SessionAttributeName.USER_DATA_SESSION, userData);
        session.removeAttribute(UPDATE_PERSONAL_INFO_RESULT);
        userData.put(LOGIN_SESSION, (String) session.getAttribute(LOGIN_SESSION));

        int roleId = (int) session.getAttribute(ROLE_SESSION);
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        Router router;

        if (roleId == UserRole.getRoleId(ADMIN_ROLE)) {
            router = new Router(PagePath.ADMIN_ACCOUNT_PAGE);
        } else {
            router = new Router(PagePath.CUSTOMER_ACCOUNT_PAGE);
        }


        return router;

    }
}
