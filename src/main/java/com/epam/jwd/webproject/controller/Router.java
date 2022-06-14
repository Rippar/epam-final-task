package com.epam.jwd.webproject.controller;

import com.epam.jwd.webproject.controller.constants.PagePath;
//Router для того, чтобы возвращать его из execute() вместо String
public class Router {
    private String page = PagePath.INDEX_PAGE;
    private Type type = Type.FORWARD;

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    enum Type{
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
