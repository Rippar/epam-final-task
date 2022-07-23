package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code UserService} interface defines the business-logic methods for working with the {@link User} objects
 *
 * @author Dmitry Murzo
 */
public interface UserService {

    /**
     * Finds all users exists in the system
     *
     * @return the list of users
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Creates new user's account according to the given user's data
     *
     * @param userData the user's data
     * @return account's creation result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean createNewAccount(Map<String, String> userData) throws ServiceException;

    /**
     * Updates exists user's account according to the given user's data
     *
     * @param userData the user's data
     * @return account's update result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean updateUserPersonalInfo(Map<String, String> userData) throws ServiceException;

    /**
     * Authenticates user according to the given user's data
     *
     * @param userData the user's data
     * @return an Optional containing user, or an empty Optional if a user has not been found or login or password wrong
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    Optional<User> authentication(Map<String, String> userData) throws ServiceException;

    /**
     * Changes the current user's password
     *
     * @param passwordData the password's data
     * @return change password result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean changePassword(Map<String, String> passwordData) throws ServiceException;

    /**
     * Inactivate the specific user in the system
     *
     * @param userData the user's data
     * @return inactivate user result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean inactivateUser(Map<String, String> userData) throws ServiceException;
}
