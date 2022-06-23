package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        return new Router(PagePath.MAIN_PAGE);
    }
}
