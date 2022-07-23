package com.epam.jwd.carrentproject.controller.listener;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code SessionCreateListenerImpl} class implements the functional of {@link HttpSessionListener}
 *
 * @author Dmitry Murzo
 */
@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.log(Level.INFO, "Session created :" + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.log(Level.INFO, "Session destroyed :" + se.getSession().getId());
    }


}
