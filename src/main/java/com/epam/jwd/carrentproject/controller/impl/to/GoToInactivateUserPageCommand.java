package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.entity.User;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import com.epam.jwd.carrentproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

/**
 * The {@code GoToInactivateUserPage} class implements the functional of {@link Command}
 * The class executes the command to go to the inactivate user page
 *
 * @author Dmitry Murzo
 */
public class GoToInactivateUserPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the command to go to the inactivate user page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(INACTIVATE_USER_RESULT);
        UserService userService = ServiceProvider.getInstance().getUserService();

        List<User> users;

        try {
            users = userService.findAllUsers();
            session.setAttribute(USERS_LIST_TO_INACTIVATE_SESSION, users);

        } catch (ServiceException e) {
            LOGGER.error("Try to find all users was failed.", e);
            throw new CommandException("Try to find all users was failed.", e);
        }

        return new Router(PagePath.INACTIVATE_USER_PAGE);
    }
}
