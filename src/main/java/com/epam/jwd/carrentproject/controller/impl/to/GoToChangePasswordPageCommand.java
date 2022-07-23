package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The {@code GoToChangePasswordPageCommand} class implements the functional of {@link Command}
 * The class executes the command to go to the change password page
 *
 * @author Dmitry Murzo
 */
public class GoToChangePasswordPageCommand implements Command {

    /**
     * The method executes the command to go to the change password page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);

        return new Router(PagePath.CHANGE_PASSWORD_PAGE);
    }
}
