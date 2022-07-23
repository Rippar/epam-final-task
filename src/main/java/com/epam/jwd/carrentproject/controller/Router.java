package com.epam.jwd.carrentproject.controller;

import jakarta.servlet.http.HttpServlet;

/**
 * The {@code Router} class represents the page transition type
 *
 * @author Dmitry Murzo
 */

public class Router {
    private String page;
    private Type type = Type.FORWARD;

    public Router(String page) {
        this.page = page;
    }

    /**
     * Instantiates a new Router.
     *
     * @param page the page
     * @param type the transition type
     */
    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    /**
     * The transition type.
     */
    public enum Type {
        FORWARD, REDIRECT
    }

    /**
     * Gets a page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Gets a type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets a page.
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Sets the redirect type.
     */
    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
