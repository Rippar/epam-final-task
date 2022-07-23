package com.epam.jwd.carrentproject.service.validator.impl;

import com.epam.jwd.carrentproject.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;

/**
 * The {@code UserValidatorImpl} class implements the functional of {@link UserValidator}
 * The class implements the methods to validate user's data
 *
 * @author Dmitry Murzo
 */
public class UserValidatorImpl implements UserValidator {
    private static final String LOGIN_REGEX = "[\\p{Alpha}][\\p{Alpha}\\d]{4,15}";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-яё\\p{Lower}]{1,15}";
    private static final String SURNAME_REGEX = "[А-Я\\p{Upper}][а-я\\p{Lower}]{1,20}";
    private static final String EMAIL_REGEX = "(([\\p{Alpha}\\d._]+){5,25}@([\\p{Lower}]+){3,7}\\.([\\p{Lower}]+){2," +
            "3})";
    private static final String PASSPORT_REGEX = "[А-Я]{2}[0-9]{7}";
    private static final String INTEGER_CHECK = "\\d+";

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public boolean validateUserId(String id) {
        return id.matches(INTEGER_CHECK);
    }

    @Override
    public boolean validateLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean validateName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean validateSurname(String surname) {
        return surname != null && surname.matches(SURNAME_REGEX);
    }

    @Override
    public boolean validateEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean validatePassportNum(String passportNumber) {
        return passportNumber != null && passportNumber.matches(PASSPORT_REGEX);
    }

    @Override
    public boolean validateUserPersonalDataWhenCreate(Map<String, String> userData) {
        String login = userData.get(LOGIN_SESSION);
        String password = userData.get(PASSWORD_SESSION);
        String repeatPassword = userData.get(MISMATCH_PASSWORDS_SESSION);
        String firstName = userData.get(FIRST_NAME_SESSION);
        String lastName = userData.get(LAST_NAME_SESSION);
        String email = userData.get(EMAIL_SESSION);
        String passportNum = userData.get(PASSPORT_SESSION);

        boolean isValid = true;
        if (!validateLogin(login)) {
            userData.put(WRONG_LOGIN_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid login.");
            isValid = false;
        }
        if (!validatePassword(password)) {
            userData.put(WRONG_PASSWORD_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid password.");
            isValid = false;
        }
        if (!password.equals(repeatPassword)) {
            userData.put(MISMATCH_PASSWORDS_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Mismatch passwords.");
            isValid = false;
        }
        if (!validateName(firstName)) {
            userData.put(WRONG_FIRST_NAME_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid first name.");
            isValid = false;
        }
        if (!validateName(lastName)) {
            userData.put(WRONG_LAST_NAME_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid last name.");
            isValid = false;
        }
        if (!validateEmail(email)) {
            userData.put(WRONG_EMAIL_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid email.");
            isValid = false;
        }

        if (!validatePassportNum(passportNum)) {
            userData.put(WRONG_EMAIL_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid passport number.");
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean validateUserPersonalDataWhenUpdate(Map<String, String> userData) {
        String firstName = userData.get(FIRST_NAME_SESSION);
        String lastName = userData.get(LAST_NAME_SESSION);
        String email = userData.get(EMAIL_SESSION);

        boolean isValid = true;

        if (!validateName(firstName)) {
            userData.put(WRONG_FIRST_NAME_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid first name.");
            isValid = false;
        }
        if (!validateName(lastName)) {
            userData.put(WRONG_LAST_NAME_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid last name.");
            isValid = false;
        }
        if (!validateEmail(email)) {
            userData.put(WRONG_EMAIL_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid email.");
            isValid = false;
        }

        return isValid;
    }


}
