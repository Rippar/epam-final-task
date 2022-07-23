package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The {@code GoToMainPageCommand} class implements the functional of {@link Command}
 * The class executes the command to go to the main page
 *
 * @author Dmitry Murzo
 */
public class GoToMainPageCommand implements Command {

    /**
     * The method executes the command to go to the main page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        return new Router(PagePath.MAIN_PAGE);
    }
}
