package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

/**
 * @project Car rent
 * @author Dmitry Murzo
 * The interface User validator.
 */
public interface UserValidator {

    /**
     * Validate user's id for update boolean.
     *
     * @param id user's id
     * @return the boolean
     */
    boolean validateUserId(String id);

    /**
     * Validate login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean validateLogin(String login);

    /**
     * Validate password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean validatePassword(String password);

    /**
     * Validate name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean validateName(String name);

    /**
     * Validate surname boolean.
     *
     * @param surname the name
     * @return the boolean
     */
    boolean validateSurname(String surname);

    /**
     * Validate email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean validateEmail(String email);

    /**
     * Validate passport number boolean.
     *
     * @param passportNumber the passport number
     * @return the boolean
     */
    boolean validatePassportNum(String passportNumber);

    /**
     * Validate user data for create boolean.
     *
     * @param userData the user data
     * @return the boolean
     */
    boolean validateUserPersonalDataWhenCreate(Map<String, String> userData);

    /**
     * Validate user data for update boolean.
     *
     * @param userData the user data
     * @return the boolean
     */
    boolean validateUserPersonalDataWhenUpdate(Map<String, String> userData);
}
