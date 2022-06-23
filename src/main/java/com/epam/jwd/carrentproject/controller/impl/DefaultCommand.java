package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX_PAGE);
    }
}
