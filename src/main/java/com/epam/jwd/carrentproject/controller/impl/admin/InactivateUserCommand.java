package com.epam.jwd.carrentproject.controller.impl.admin;

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
 * The {@code InactivateUserCommand} class implements the functional of {@link Command}
 * The class executes the command to inactivate the exists user in the system
 *
 * @author Dmitry Murzo
 */
public class InactivateUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the inactivate user command, writes an additional info to the user's data and session's
     * attributes
     */
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
            boolean result = userService.inactivateUser(userData);
            int sizeAfter = userData.size();

            if (sizeBefore != sizeAfter) {
                session.setAttribute(USER_DATA_SESSION, userData);
            }

            session.setAttribute(INACTIVATE_USER_RESULT, result);

            session.setAttribute(CURRENT_PAGE, PagePath.INACTIVATE_USER_PAGE);
            router = new Router(PagePath.INACTIVATE_USER_PAGE);

        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to inactivate user's account.", e);
            throw new CommandException("Unsuccessful attempt to inactivate user's account.", e);
        }
        return router;
    }

    /**
     * Removes the temporary data from user's data
     *
     * @param userData the user's data
     */
    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_ID_SESSION);

    }

    /**
     * Updates user's data from request
     *
     * @param request  a request from controller
     * @param userData the user's data
     */
    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(USER_ID_TO_INACTIVATE_SESSION, request.getParameter(USER_ID_TO_INACTIVATE));

    }
}
