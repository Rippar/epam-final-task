package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

/**
 * The {@code UserService} interface defines the methods to validate user's data
 *
 * @author Dmitry Murzo
 */
public interface UserValidator {

    /**
     * Validates user's id for update
     *
     * @param id user's id
     * @return the boolean
     */
    boolean validateUserId(String id);

    /**
     * Validates login
     *
     * @param login the login
     * @return the boolean
     */
    boolean validateLogin(String login);

    /**
     * Validates password
     *
     * @param password the password
     * @return the boolean
     */
    boolean validatePassword(String password);

    /**
     * Validates name
     *
     * @param name the name
     * @return the boolean
     */
    boolean validateName(String name);

    /**
     * Validates surname
     *
     * @param surname the name
     * @return the boolean
     */
    boolean validateSurname(String surname);

    /**
     * Validates email
     *
     * @param email the email
     * @return the boolean
     */
    boolean validateEmail(String email);

    /**
     * Validates passport number
     *
     * @param passportNumber the passport number
     * @return the boolean
     */
    boolean validatePassportNum(String passportNumber);

    /**
     * Validates user's data for create boolean.
     *
     * @param userData the user's data
     * @return the boolean
     */
    boolean validateUserPersonalDataWhenCreate(Map<String, String> userData);

    /**
     * Validates user's data for update
     *
     * @param userData the user's data
     * @return the boolean
     */
    boolean validateUserPersonalDataWhenUpdate(Map<String, String> userData);
}
