package com.epam.jwd.carrentproject.controller.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code ServletContextListenerImpl} class implements the functional of {@link ServletContextListener}
 *
 * @author Dmitry Murzo
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.log(Level.INFO, "Context initialized :" + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.log(Level.INFO, "Context destroyed :" + sce.getServletContext().getContextPath());
    }


}
