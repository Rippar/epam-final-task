package com.epam.jwd.carrentproject.controller.filter;

import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestParameterName;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Set;

import static com.epam.jwd.carrentproject.controller.constant.CommandName.*;

@WebFilter(filterName = "PreControllerFilter", urlPatterns = "/controller")
public class PreControllerFilter implements Filter {
    static Logger logger = LogManager.getLogger();

    private static final int ADMIN_ROLE_ID = 1;
    private static final int CUSTOMER_ROLE_ID = 2;
    private static final int GUEST_ROLE_ID = 3;

    private Set<String> guestCommands;
    private Set<String> customerCommands;
    private Set<String> adminCommands;
    private Set<String> allCommands;

    public void init(FilterConfig config) throws ServletException {
        guestCommands = Set.of(LOGIN_COMMAND, DEFAULT_COMMAND, REGISTRATION_USER_COMMAND,
                GO_TO_REGISTRATION_PAGE_COMMAND, GO_TO_MAIN_PAGE_COMMAND, GO_TO_LOGIN_PAGE_COMMAND);

        customerCommands = Set.of(LOGOUT_COMMAND, DEFAULT_COMMAND, GO_TO_MAIN_PAGE_COMMAND,
                UPDATE_USER_PERSONAL_INFO_COMMAND, GO_TO_ACCOUNT_PAGE_COMMAND, GO_TO_HOMEPAGE_COMMAND,
                GO_TO_CHANGE_PERSONAL_INFO_PAGE_COMMAND, GO_TO_CHANGE_PASSWORD_PAGE_COMMAND, CHANGE_PASSWORD_COMMAND);

        adminCommands = Set.of(LOGOUT_COMMAND, DEFAULT_COMMAND, FIND_ALL_USERS_COMMAND, GO_TO_MAIN_PAGE_COMMAND,
                UPDATE_USER_PERSONAL_INFO_COMMAND, GO_TO_ACCOUNT_PAGE_COMMAND, GO_TO_HOMEPAGE_COMMAND,
                GO_TO_CHANGE_PERSONAL_INFO_PAGE_COMMAND, GO_TO_CHANGE_PASSWORD_PAGE_COMMAND, CHANGE_PASSWORD_COMMAND,
                GO_TO_INACTIVATE_USER_PAGE_COMMAND, INACTIVATE_USER_COMMAND, GO_TO_ADD_CAR_PAGE_COMMAND,
                ADD_CAR_COMMAND, GO_TO_UPDATE_CAR_PAGE_COMMAND, UPDATE_CAR_COMMAND, FIND_ALL_CARS_COMMAND,
                INACTIVATE_CAR_COMMAND, GO_TO_INACTIVATE_CAR_PAGE_COMMAND);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException
            , IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        logger.log(Level.INFO, "+++++++++> Session In PreControllerFilter :" + (session != null ? session.getId() :
                "session not created"));

        String commandName = httpServletRequest.getParameter(RequestParameterName.COMMAND);

        if (commandName == null) {
            httpServletResponse.sendRedirect(PagePath.INDEX_PAGE);
            return;
        }

        int roleId = (session.getAttribute(SessionAttributeName.CURRENT_ROLE) == null ? GUEST_ROLE_ID :
                (int) session.getAttribute(SessionAttributeName.CURRENT_ROLE));

        boolean isAcceptable;
        if (roleId == ADMIN_ROLE_ID) {
            isAcceptable = adminCommands.stream().anyMatch(commandName::contains);

        } else if (roleId == CUSTOMER_ROLE_ID) {
            isAcceptable = customerCommands.stream().anyMatch(commandName::contains);
        } else {
            isAcceptable = guestCommands.stream().anyMatch(commandName::contains);
        }

        if (isAcceptable) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

    }

    public void destroy() {
    }
}
