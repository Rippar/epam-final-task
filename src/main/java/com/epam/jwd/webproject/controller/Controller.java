package com.epam.jwd.webproject.controller;

import java.io.*;

import com.epam.jwd.webproject.controller.constants.PagePath;
import com.epam.jwd.webproject.controller.constants.RequestParameterName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    public void init() {
        logger.log(Level.INFO, "----------> Servlet Init :" + this.getServletInfo());

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            request.getRequestDispatcher(PagePath.ERROR_500).forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        String commandName = request.getParameter(RequestParameterName.COMMAND_PARAM_NAME);
        Command command = CommandProvider.getCommand(commandName);
        Router router = command.execute(request);
        switch (router.getType()) {
            case FORWARD -> request.getRequestDispatcher(router.getPage()).forward(request, response);
            case REDIRECT -> response.sendRedirect(request.getContextPath() + router.getPage());

        }
    }

    public void destroy() {
        logger.log(Level.INFO, "----------> Servlet Destroyed :" + this.getServletName());

    }
}