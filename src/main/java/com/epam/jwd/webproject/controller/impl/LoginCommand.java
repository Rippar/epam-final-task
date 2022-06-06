package com.epam.jwd.webproject.controller.impl;

import com.epam.jwd.webproject.controller.Command;
import com.epam.jwd.webproject.controller.CommandException;
import com.epam.jwd.webproject.controller.constants.PagePass;
import com.epam.jwd.webproject.controller.constants.RequestAttributeName;
import com.epam.jwd.webproject.controller.constants.RequestParameterName;
import com.epam.jwd.webproject.service.ServiceException;
import com.epam.jwd.webproject.service.ServiceProvider;
import com.epam.jwd.webproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        //вынести в константы login и password (отдельный класс со всеми параметрами request)
        String login = request.getParameter(RequestParameterName.LOGIN_PARAM_NAME);
        String password = request.getParameter(RequestParameterName.PASSWORD_PARAM_NAME);
        UserService userService = ServiceProvider.getInstance().getUserService();
        String page;

        try {
            if (userService.authentication(login, password)) {
                request.setAttribute(RequestAttributeName.USER_ATTRIBUTE, login);
                page = PagePass.MAIN_PAGE;
            } else {
                // Блинов сказал, что строка "incorrect login or pass" должна быть локализованной
                request.setAttribute(RequestAttributeName.LOGIN_MSG_ATTRIBUTE, "incorrect login or pass");
                page = PagePass.INDEX_PAGE;
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
