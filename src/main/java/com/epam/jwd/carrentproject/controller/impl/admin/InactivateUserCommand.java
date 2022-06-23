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

public class InactivateUserCommand implements Command {

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
            boolean result = userService.inactivateUser(userData);
            int sizeAfter = userData.size();

            if (sizeBefore == sizeAfter) {
                //session.removeAttribute(USER_DATA_SESSION);
            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
            }
            session.setAttribute(INACTIVATE_USER_RESULT, result);

            session.setAttribute(CURRENT_PAGE, PagePath.INACTIVATE_USER_PAGE);
            router = new Router(PagePath.INACTIVATE_USER_PAGE);

        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to inactivate user's account.", e);
            throw new CommandException("Unsuccessful attempt to inactivate user's account.", e);
        }
        return router;
    }

    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_ID_SESSION);

    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(USER_ID_TO_INACTIVATE_SESSION, request.getParameter(USER_ID_TO_INACTIVATE));



    }
}
