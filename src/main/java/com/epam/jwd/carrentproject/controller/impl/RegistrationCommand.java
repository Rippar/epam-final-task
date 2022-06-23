package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import com.epam.jwd.carrentproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.*;

public class RegistrationCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);
        UserService userService = ServiceProvider.getInstance().getUserService();
        Router router;

        try {
            int sizeBefore = userData.size();
            boolean result = userService.createNewAccount(userData);
            int sizeAfter = userData.size();

            if (sizeBefore == sizeAfter) {
                session.removeAttribute(USER_DATA_SESSION);

            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
            }
            session.setAttribute(REGISTRATION_RESULT, result);
            if(result) {
                session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
                router = new Router(PagePath.MAIN_PAGE);
            } else {
                session.setAttribute(CURRENT_PAGE, PagePath.REGISTRATION_PAGE);
                router = new Router(PagePath.REGISTRATION_PAGE);
            }


        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to create new account.", e);
            throw new CommandException("Unsuccessful attempt to create new account.", e);
        }
        return router;
    }

    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_LOGIN_SESSION);
        userData.remove(WRONG_EMAIL_SESSION);
        userData.remove(WRONG_PASSWORD_SESSION);
        userData.remove(WRONG_FIRST_NAME_SESSION);
        userData.remove(WRONG_LAST_NAME_SESSION);
        userData.remove(MISMATCH_PASSWORDS_SESSION);
        userData.remove(WRONG_LOGIN_EXISTS_SESSION);
        userData.remove(WRONG_PASSPORT_EXISTS_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(LOGIN_SESSION, request.getParameter(LOGIN));
        userData.put(EMAIL_SESSION, request.getParameter(EMAIL));
        userData.put(FIRST_NAME_SESSION, request.getParameter(FIRST_NAME));
        userData.put(LAST_NAME_SESSION, request.getParameter(LAST_NAME));
        userData.put(PASSPORT_SESSION, request.getParameter(PASSPORT));
        userData.put(PASSWORD_SESSION, request.getParameter(PASSWORD));
        userData.put(MISMATCH_PASSWORDS_SESSION, request.getParameter(REPEAT_PASSWORD));

    }
}
