package com.epam.jwd.carrentproject.controller.filter;

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
import java.util.HashSet;
import java.util.Set;

import static com.epam.jwd.carrentproject.controller.constant.PagePath.*;


@WebFilter(filterName = "PageRedirectSecurityFilter", urlPatterns = {"/pages/*"})
public class PageRedirectSecurityFilter implements Filter {

    static Logger logger = LogManager.getLogger();

    private static final int ADMIN_ROLE_ID = 1;
    private static final int CUSTOMER_ROLE_ID = 2;

    private Set<String> guestPages;
    private Set<String> customerPages;
    private Set<String> adminPages;
    private Set<String> allPages;

    @Override
    public void init(FilterConfig filterConfig) {
        guestPages = Set.of(LOGIN_PAGE, REGISTRATION_PAGE, MAIN_PAGE);

        customerPages = Set.of(LOGIN_PAGE, REGISTRATION_PAGE, MAIN_PAGE, HOME_PAGE, CUSTOMER_ACCOUNT_PAGE,
                CHANGE_PERSONAL_INFO_FORM, CHANGE_PASSWORD_FORM);

        adminPages = Set.of(LOGIN_PAGE, REGISTRATION_PAGE, MAIN_PAGE, HOME_PAGE, ADMIN_ACCOUNT_PAGE, ALL_USERS_PAGE,
                CHANGE_PERSONAL_INFO_FORM, CHANGE_PASSWORD_FORM, INACTIVATE_USER_PAGE, ADD_CAR_PAGE, UPDATE_CAR_PAGE,
                ALL_CARS_PAGE, INACTIVATE_CAR_PAGE);

        allPages = new HashSet<>();
        allPages.addAll(guestPages);
        allPages.addAll(customerPages);
        allPages.addAll(adminPages);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.log(Level.INFO, "--------xxxxxx-------- >>>>>>Start PageRedirectSecurityFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getServletPath();
        //returns true if it contains at least one element
        boolean isPageExist = allPages.stream().anyMatch(requestURI::contains);

        if (isPageExist) {
            HttpSession session = request.getSession();
            int roleId = (int) session.getAttribute(SessionAttributeName.CURRENT_ROLE);

            boolean isAcceptable;
            if (roleId == ADMIN_ROLE_ID) {
                isAcceptable = adminPages.stream().anyMatch(requestURI::contains);
            } else if (roleId == CUSTOMER_ROLE_ID) {
                isAcceptable = customerPages.stream().anyMatch(requestURI::contains);
            } else {
                isAcceptable = guestPages.stream().anyMatch(requestURI::contains);
            }

            if (!isAcceptable) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        logger.log(Level.INFO, "--------xxxxxx-------- >>>>>>End PageRedirectSecurityFilter");

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
