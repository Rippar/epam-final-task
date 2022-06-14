package com.epam.jwd.webproject.controller;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute (HttpServletRequest request) throws CommandException;
    default void refresh(){};
}
