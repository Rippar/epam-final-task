package com.epam.jwd.webproject.controller.impl;

import com.epam.jwd.webproject.controller.Command;
import com.epam.jwd.webproject.controller.CommandException;
import com.epam.jwd.webproject.controller.Router;
import com.epam.jwd.webproject.controller.constants.PagePath;
import com.epam.jwd.webproject.controller.constants.RequestAttributeName;
import com.epam.jwd.webproject.controller.constants.RequestParameterName;
import com.epam.jwd.webproject.controller.constants.SessionAttributeName;
import com.epam.jwd.webproject.service.ServiceException;
import com.epam.jwd.webproject.service.ServiceProvider;
import com.epam.jwd.webproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        //вынести в константы login и password (отдельный класс со всеми параметрами request)
        String login = request.getParameter(RequestParameterName.LOGIN_PARAM_NAME);
        String password = request.getParameter(RequestParameterName.PASSWORD_PARAM_NAME);
        UserService userService = ServiceProvider.getInstance().getUserService();
        String page;
        HttpSession session = request.getSession();

        try {
            if (userService.authentication(login, password)) {
                request.setAttribute(RequestAttributeName.USER_ATTRIBUTE, login);
                //сюда добавляем локаль, роль и текущую страницу
                session.setAttribute(SessionAttributeName.USER_ATTRIBUTE, login);
                page = PagePath.MAIN_PAGE;
            } else {
                // Блинов сказал, что строка "incorrect login or pass" должна быть локализованной
                request.setAttribute(RequestAttributeName.LOGIN_MSG_ATTRIBUTE, "incorrect login or pass");
                page = PagePath.INDEX_PAGE;
            }
            //для возврата на ту же самую страницу при смене локали
            session.setAttribute(SessionAttributeName.CURRENT_PAGE, page);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(page);
    }
}
