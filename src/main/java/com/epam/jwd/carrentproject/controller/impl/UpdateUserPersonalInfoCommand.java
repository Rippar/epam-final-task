package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
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

public class UpdateUserPersonalInfoCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final int ADMIN_ROLE_ID = 1;
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);
        userData.put(LOGIN_SESSION, (String) session.getAttribute(LOGIN_SESSION));


        UserService userService = ServiceProvider.getInstance().getUserService();
        Router router;

        try {
            int sizeBefore = userData.size();
            boolean result = userService.updateUserPersonalInfo(userData);
            int sizeAfter = userData.size();

            if (sizeBefore == sizeAfter) {
                session.removeAttribute(USER_DATA_SESSION);
            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
            }
            session.setAttribute(UPDATE_PERSONAL_INFO_RESULT, result);


            int roleId = (int) session.getAttribute(SessionAttributeName.CURRENT_ROLE);

            if (roleId == ADMIN_ROLE_ID) {
                session.setAttribute(CURRENT_PAGE, PagePath.ADMIN_ACCOUNT_PAGE);
                router = new Router(PagePath.CHANGE_PERSONAL_INFO_FORM, Router.Type.REDIRECT);
            } else {
                session.setAttribute(CURRENT_PAGE, PagePath.CUSTOMER_ACCOUNT_PAGE);
                router = new Router(PagePath.CHANGE_PERSONAL_INFO_FORM, Router.Type.REDIRECT);
            }

        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to update account's info.", e);
            throw new CommandException("Unsuccessful attempt to update account's info.", e);
        }
        return router;
    }

    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_FIRST_NAME_SESSION);
        userData.remove(WRONG_LAST_NAME_SESSION);
        userData.remove(WRONG_EMAIL_SESSION);
        userData.remove(PASSWORD_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(EMAIL_SESSION, request.getParameter(EMAIL));
        userData.put(FIRST_NAME_SESSION, request.getParameter(FIRST_NAME));
        userData.put(LAST_NAME_SESSION, request.getParameter(LAST_NAME));
        userData.put(PASSWORD_SESSION, request.getParameter(PASSWORD));


    }
}
