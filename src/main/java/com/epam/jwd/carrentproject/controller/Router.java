package com.epam.jwd.carrentproject.controller;

import com.epam.jwd.carrentproject.controller.constant.PagePath;
//Router для того, чтобы возвращать его из execute() вместо String
public class Router {
    private String page;
    private Type type = Type.FORWARD;

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public enum Type{
        FORWARD, REDIRECT;
    }

    public String getPage() {
        return page;
    }

    public Type getType() {
        return type;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
