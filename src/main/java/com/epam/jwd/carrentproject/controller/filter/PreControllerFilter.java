package com.epam.jwd.carrentproject.controller.filter;

import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestParameterName;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.Set;

import static com.epam.jwd.carrentproject.controller.constant.CommandName.*;
import static com.epam.jwd.carrentproject.entity.UserRole.*;

/**
 * The {@code PreControllerFilter} class implements the functional of {@link Filter}
 * The class restricts the access to the commands depending on the user's role.
 *
 * @author Dmitry Murzo
 */

@WebFilter(filterName = "PreControllerFilter", urlPatterns = "/controller")
public class PreControllerFilter implements Filter {

    private Set<String> guestCommands;
    private Set<String> customerCommands;
    private Set<String> adminCommands;


    public void init(FilterConfig config) throws ServletException {
        guestCommands = Set.of(LOGIN_COMMAND, DEFAULT_COMMAND, REGISTRATION_USER_COMMAND,
                GO_TO_REGISTRATION_PAGE_COMMAND, GO_TO_MAIN_PAGE_COMMAND, GO_TO_LOGIN_PAGE_COMMAND,
                CHANGE_LANGUAGE_COMMAND, LOGOUT_COMMAND);

        customerCommands = Set.of(LOGOUT_COMMAND, DEFAULT_COMMAND, GO_TO_MAIN_PAGE_COMMAND,
                UPDATE_USER_PERSONAL_INFO_COMMAND, GO_TO_ACCOUNT_PAGE_COMMAND, GO_TO_HOMEPAGE_COMMAND,
                GO_TO_CHANGE_PERSONAL_INFO_PAGE_COMMAND, GO_TO_CHANGE_PASSWORD_PAGE_COMMAND, CHANGE_PASSWORD_COMMAND,
                GO_TO_ORDER_PAGE_COMMAND, ADD_ORDER_COMMAND, GO_TO_USER_ORDERS_PAGE_COMMAND,
                GO_TO_CHANGE_ORDER_PAGE_COMMAND, INACTIVATE_ORDER_COMMAND, CHANGE_LANGUAGE_COMMAND);

        adminCommands = Set.of(LOGOUT_COMMAND, DEFAULT_COMMAND, FIND_ALL_USERS_COMMAND, GO_TO_MAIN_PAGE_COMMAND,
                UPDATE_USER_PERSONAL_INFO_COMMAND, GO_TO_ACCOUNT_PAGE_COMMAND, GO_TO_HOMEPAGE_COMMAND,
                GO_TO_CHANGE_PERSONAL_INFO_PAGE_COMMAND, GO_TO_CHANGE_PASSWORD_PAGE_COMMAND, CHANGE_PASSWORD_COMMAND,
                GO_TO_INACTIVATE_USER_PAGE_COMMAND, INACTIVATE_USER_COMMAND, GO_TO_ADD_CAR_PAGE_COMMAND,
                ADD_CAR_COMMAND, GO_TO_UPDATE_CAR_PAGE_COMMAND, UPDATE_CAR_COMMAND, FIND_ALL_CARS_COMMAND,
                INACTIVATE_CAR_COMMAND, GO_TO_INACTIVATE_CAR_PAGE_COMMAND, GO_TO_ORDER_PAGE_COMMAND, ADD_ORDER_COMMAND,
                GO_TO_USER_ORDERS_PAGE_COMMAND, GO_TO_CHANGE_ORDER_PAGE_COMMAND, INACTIVATE_ORDER_COMMAND,
                FIND_ALL_ORDERS_COMMAND, GO_TO_CONFIRM_ORDERS_PAGE_COMMAND, CONFIRM_ORDER_COMMAND,
                GO_TO_CANCEL_ORDERS_PAGE_COMMAND, CANCEL_ORDER_COMMAND, GO_TO_COMPLETE_ORDERS_PAGE_COMMAND,
                COMPLETE_ORDER_COMMAND, FIND_ALL_RETURN_FORMS_COMMAND, CHANGE_LANGUAGE_COMMAND);

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException
            , IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        String commandName = httpServletRequest.getParameter(RequestParameterName.COMMAND);

        if (commandName == null) {
            httpServletResponse.sendRedirect(PagePath.INDEX_PAGE);
            return;
        }

        int roleId = (session.getAttribute(SessionAttributeName.CURRENT_ROLE) == null ? UserRole.getRoleId(GUEST_ROLE) :
                (int) session.getAttribute(SessionAttributeName.CURRENT_ROLE));

        boolean isAcceptable;
        if (roleId == UserRole.getRoleId(ADMIN_ROLE)) {
            isAcceptable = adminCommands.stream().anyMatch(commandName::contains);

        } else if (roleId == UserRole.getRoleId(CUSTOMER_ROLE)) {
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
