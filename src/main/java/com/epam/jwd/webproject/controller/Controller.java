package com.epam.jwd.webproject.controller;

import java.io.*;

import com.epam.jwd.webproject.controller.constants.RequestParameterName;
import com.epam.jwd.webproject.pool.ConnectionPool;
import com.epam.jwd.webproject.pool.PoolProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
//        String strNum = request.getParameter("num");
//        int resNum = 2*Integer.parseInt(strNum);
//        request.setAttribute("result", resNum);
        String commandName = request.getParameter(RequestParameterName.COMMAND_PARAM_NAME);
        Command command = CommandProvider.getCommand(commandName);
        String page = null;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            //response.sendError(500); //1 вариант обработки исключения
            //throw new ServletException(e); //2 вариант обработки исключения
            request.setAttribute("error_msg", e.getCause()); //3 вариант обработки исключения
            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
        ConnectionPool cp = PoolProvider.getInstance().getConnectionPool();
        cp.dispose();
    }
}