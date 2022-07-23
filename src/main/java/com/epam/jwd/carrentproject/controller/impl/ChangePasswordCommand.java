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

/**
 * The {@code ChangePasswordCommand} class implements the functional of {@link Command}
 * The class executes the command to change the user's password
 *
 * @author Dmitry Murzo
 */
public class ChangePasswordCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the change password command, writes an additional info to the user's data and session's
     * attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);

        userData.put(LOGIN_SESSION, (String) session.getAttribute(LOGIN_SESSION));
        userData.put(USER_ID_SESSION, String.valueOf(session.getAttribute(USER_ID_SESSION)));

        UserService userService = ServiceProvider.getInstance().getUserService();
        Router router;

        try {
            int sizeBefore = userData.size();
            boolean result = userService.changePassword(userData);
            int sizeAfter = userData.size();

            if (sizeBefore != sizeAfter) {
                session.setAttribute(USER_DATA_SESSION, userData);
            }
            session.setAttribute(CHANGE_PASSWORD_RESULT, result);
            router = new Router(PagePath.CHANGE_PASSWORD_PAGE);


        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to update password.", e);
            throw new CommandException("Unsuccessful attempt to update password.", e);
        }
        return router;
    }

    /**
     * Removes the temporary data from user's data
     *
     * @param userData the user's data
     */
    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_PASSWORD_SESSION);
        userData.remove(WRONG_NEW_PASSWORD_SESSION);
        userData.remove(WRONG_OLD_PASSWORD_SESSION);
        userData.remove(WRONG_REPEATED_NEW_PASSWORD_SESSION);
    }

    /**
     * Updates user's data from request
     *
     * @param request  a request from controller
     * @param userData the user's data
     */
    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(PASSWORD_SESSION, request.getParameter(PASSWORD));
        userData.put(NEW_PASSWORD_SESSION, request.getParameter(NEW_PASSWORD));
        userData.put(REPEAT_NEW_PASSWORD_SESSION, request.getParameter(REPEAT_NEW_PASSWORD));

    }
}
