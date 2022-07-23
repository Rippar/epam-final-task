package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

/**
 * The {@code ChangeLanguageCommand} class implements the functional of {@link Command}
 * The class executes the command to change the language of the system
 *
 * @author Dmitry Murzo
 */
public class ChangeLanguageCommand implements Command {
    private static final String ENGLISH_LANGUAGE = "en_EN";
    private static final String RUSSIAN_LANGUAGE = "ru_RU";

    /**
     * The method executes the change language command, writes an additional info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String currentLanguage = (String) session.getAttribute(LANGUAGE_SESSION);

        if (currentLanguage == null || currentLanguage.equals(RUSSIAN_LANGUAGE)) {
            session.setAttribute(LANGUAGE_SESSION, ENGLISH_LANGUAGE);
        } else {
            session.setAttribute(LANGUAGE_SESSION, RUSSIAN_LANGUAGE);
        }

        return new Router(currentPage);

    }
}
