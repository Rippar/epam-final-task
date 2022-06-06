package com.epam.jwd.webproject.controller.impl;

import com.epam.jwd.webproject.controller.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        //вынести в константу возврат на главную страницу (index.jsp)
        return "index.jsp";
    }
}
