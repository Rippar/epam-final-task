package com.epam.jwd.carrentproject.controller;

import java.io.*;

import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestParameterName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code Controller} class represents complex form response of {@link Command}
 * The class processes all requests after filtering.
 *
 * @author Dmitry Murzo
 */

@WebServlet(name = "helloServlet", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    public void init() {
        LOGGER.log(Level.INFO, "Servlet Init :" + this.getServletInfo());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            request.getRequestDispatcher(PagePath.ERROR_500_PAGE).forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            request.getRequestDispatcher(PagePath.ERROR_500_PAGE).forward(request, response);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, CommandException {

        String commandName = request.getParameter(RequestParameterName.COMMAND);
        Command command = CommandProvider.getCommand(commandName);
        Router router = command.execute(request);
        switch (router.getType()) {
            case FORWARD -> request.getRequestDispatcher(router.getPage()).forward(request, response);
            case REDIRECT -> response.sendRedirect(router.getPage());

        }
    }

    public void destroy() {
        LOGGER.log(Level.INFO, "Servlet Destroyed :" + this.getServletName());

    }
}