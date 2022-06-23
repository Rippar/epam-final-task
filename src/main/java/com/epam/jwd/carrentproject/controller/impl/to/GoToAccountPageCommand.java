package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

public class GoToAccountPageCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final int ADMIN_ROLE_ID = 1;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();
        session.setAttribute(SessionAttributeName.USER_DATA_SESSION, userData);
        session.removeAttribute(UPDATE_PERSONAL_INFO_RESULT);
        userData.put(LOGIN_SESSION, (String) session.getAttribute(LOGIN_SESSION));
        int roleId = (int)session.getAttribute(ROLE_SESSION);
        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        Router router;

        if(roleId==ADMIN_ROLE_ID) {
            router = new Router(PagePath.ADMIN_ACCOUNT_PAGE);
        } else {
            router = new Router(PagePath.CUSTOMER_ACCOUNT_PAGE);
        }


        return router;

    }
}
