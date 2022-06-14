package com.epam.jwd.webproject.controller.impl;

import com.epam.jwd.webproject.controller.Command;
import com.epam.jwd.webproject.controller.Router;
import com.epam.jwd.webproject.controller.constants.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.INDEX_PAGE);
    }
}
