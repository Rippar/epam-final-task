package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code GoToRegistrationPageCommand} class implements the functional of {@link Command}
 * The class executes the command to go to the registrationr page
 *
 * @author Dmitry Murzo
 */
public class GoToRegistrationPageCommand implements Command {

    /**
     * The method executes the command to go to the registration car page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttributeName.REGISTRATION_RESULT);

        Map<String, String> userData = new HashMap<>();
        session.setAttribute(SessionAttributeName.USER_DATA_SESSION, userData);

        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        return new Router(PagePath.REGISTRATION_PAGE);
    }
}
