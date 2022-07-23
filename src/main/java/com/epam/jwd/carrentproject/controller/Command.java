package com.epam.jwd.carrentproject.controller;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The {@code Command} interface defines only one abstract method which execute the required command
 *
 * @author Dmitry Murzo
 */

@FunctionalInterface
public interface Command {
    String CONTROLLER_PART = "/controller?";

    /**
     * The method which get a request from controller and returns the transition type to the page {@link Router}
     *
     * @param request a request from controller
     * @return transition type {@link Router}
     * @throws CommandException - if a method from service level throws
     *                          {@link com.epam.jwd.carrentproject.controller.CommandException}
     */
    Router execute(HttpServletRequest request) throws CommandException;

    /**
     * The method is used to extract the part with the name of the current page from request
     *
     * @param request - a request from controller
     * @return String representation of the current page from request concat with controller part
     */
    static String extract(HttpServletRequest request) {
        String commandPart = request.getQueryString();
        return CONTROLLER_PART + commandPart;
    }
}
