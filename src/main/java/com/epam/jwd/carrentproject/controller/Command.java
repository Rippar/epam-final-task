package com.epam.jwd.carrentproject.controller;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String CONTROLLER_PART = "/controller?";
    Router execute (HttpServletRequest request) throws CommandException;
    static String extract(HttpServletRequest request) {
        String commandPart = request.getQueryString();
        String currentPage = CONTROLLER_PART + commandPart;
        return currentPage;
    }
}
