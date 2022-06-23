package com.epam.jwd.carrentproject.controller.listener;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "+++<<<<----------> sessionAttributeAdded :" +sbe.getSession().getAttribute("user_name"));
        logger.log(Level.INFO, "+++<<<<----------> sessionAttributeAdded :" +sbe.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "###<<<<----------> sessionAttributeReplaced :" +sbe.getSession().getAttribute("user_name"));
        logger.log(Level.INFO, "###<<<<----------> sessionAttributeReplaced :" +sbe.getSession().getAttribute("current_page"));
    }
}
