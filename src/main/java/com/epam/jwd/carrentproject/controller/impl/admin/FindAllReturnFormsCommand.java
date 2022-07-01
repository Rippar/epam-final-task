package com.epam.jwd.carrentproject.controller.impl.admin;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestAttributeName;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.ReturnForm;
import com.epam.jwd.carrentproject.service.ReturnFormService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllReturnFormsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ReturnFormService returnFormService = ServiceProvider.getInstance().getReturnFormService();
        Router router;

        try {
            List<ReturnForm> returnFormList = returnFormService.findAllReturnForms();
            request.setAttribute(RequestAttributeName.RETURN_FORM_LIST, returnFormList);
            session.setAttribute(SessionAttributeName.CURRENT_PAGE, Command.extract(request));
            router = new Router(PagePath.ALL_RETURN_FORMS_PAGE);

        } catch (ServiceException e) {
            logger.error("Try to find all return forms was failed.", e);
            throw new CommandException("Try to find all return forms was failed.", e);
        }
        return router;
    }
}
