package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestAttributeName;
import com.epam.jwd.carrentproject.entity.User;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import com.epam.jwd.carrentproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.*;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);
        UserService userService = ServiceProvider.getInstance().getUserService();
        Router router;

        try {
            Optional<User> optionalUser = userService.authentication(userData);
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.removeAttribute(USER_DATA_SESSION);
                session.setAttribute(CURRENT_ROLE, user.getUserRoleId());
                session.setAttribute(USER_ID_SESSION, user.getUserId());
                session.setAttribute(LOGIN_SESSION, user.getLogin());
                session.setAttribute(FIRST_NAME_SESSION, user.getFirstName());
                session.setAttribute(LAST_NAME_SESSION, user.getLastName());
                session.setAttribute(ROLE_SESSION, user.getUserRoleId());
                session.setAttribute(IS_ACTIVE_USER_SESSION, user.isActive());
                session.setAttribute(EMAIL_SESSION, user.getEmail());
                session.setAttribute(PASSPORT_SESSION, user.getPassportNum());
                request.setAttribute(RequestAttributeName.USER_ATTRIBUTE, user.getFirstName());
                session.setAttribute(CURRENT_PAGE, PagePath.HOME_PAGE);
                session.setAttribute(LOGIN_RESULT, true);
                router= new Router(PagePath.HOME_PAGE);

            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
                session.setAttribute(CURRENT_PAGE, PagePath.LOGIN_PAGE);
                session.setAttribute(LOGIN_RESULT, false);
                router = new Router(PagePath.LOGIN_PAGE);
            }

        } catch (ServiceException e) {
            logger.error("Unsuccessful authentication attempt.", e);
            throw new CommandException("Unsuccessful authentication attempt.", e);
        }

        return router;

    }
    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_LOGIN_OR_PASSWORD_SESSION);
        userData.remove(NOT_FOUND_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(LOGIN_SESSION, request.getParameter(LOGIN));
        userData.put(PASSWORD_SESSION, request.getParameter(PASSWORD));

    }
}
