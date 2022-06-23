package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class GoToChangePasswordForm implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.CHANGE_PASSWORD_FORM);
    }
}
