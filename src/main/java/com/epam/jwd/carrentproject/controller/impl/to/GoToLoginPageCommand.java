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

public class GoToLoginPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();
        session.setAttribute(SessionAttributeName.USER_DATA_SESSION, userData);
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        return new Router(PagePath.LOGIN_PAGE);
    }
}
