package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

/**
 * The {@code GoToLoginPageCommand} class implements the functional of {@link Command}
 * The class executes the command to go to the login page
 *
 * @author Dmitry Murzo
 */
public class GoToLoginPageCommand implements Command {

    /**
     * The method executes the command to go to the login page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN_RESULT);
        Map<String, String> userData = new HashMap<>();

        session.setAttribute(USER_DATA_SESSION, userData);
        String currentPage = Command.extract(request);
        session.setAttribute(CURRENT_PAGE, currentPage);
        return new Router(PagePath.LOGIN_PAGE);
    }
}
