package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The {@code DefaultCommand} class implements the functional of {@link Command}
 * The class defines the command which is used as a default
 *
 * @author Dmitry Murzo
 */
public class DefaultCommand implements Command {

    /**
     * The method returns user to the index page
     */
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX_PAGE);
    }
}
