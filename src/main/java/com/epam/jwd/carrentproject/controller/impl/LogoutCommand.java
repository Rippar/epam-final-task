package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The {@code LogoutCommand} class implements the functional of {@link Command}
 * The class executes the command to logout the user from the system
 *
 * @author Dmitry Murzo
 */
public class LogoutCommand implements Command {

    /**
     * The method invalidates the session and returns user to the index page
     */
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.INDEX_PAGE);
    }
}
