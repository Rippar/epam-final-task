package com.epam.jwd.webproject.controller.impl;

import com.epam.jwd.webproject.controller.Command;
import com.epam.jwd.webproject.controller.constants.PagePass;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagePass.INDEX_PAGE;
    }
}
