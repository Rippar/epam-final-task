package com.epam.jwd.carrentproject.controller.impl.admin;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestAttributeName;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.User;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import com.epam.jwd.carrentproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllUsersCommand implements Command {

    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttributeName.UPDATE_USER_STATUS_RESULT);
        UserService userService = ServiceProvider.getInstance().getUserService();
        Router router;

        try {
            List<User> userList = userService.findAllUsers();
            request.setAttribute(RequestAttributeName.USERS_LIST, userList);
            session.setAttribute(SessionAttributeName.CURRENT_PAGE, Command.extract(request));
            router= new Router(PagePath.ALL_USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllUsersCommand was failed.", e);
            throw new CommandException("Try to execute FindAllUsersCommand was failed.", e);
        }

        return router;
    }
}
