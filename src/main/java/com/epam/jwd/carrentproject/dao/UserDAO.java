package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.User;

import java.util.Optional;

/**
 * The interface extends the functional of {@link BaseDAO}.
 *
 * @author Dmitry Murzo
 */
public interface UserDAO extends BaseDAO<Integer, User> {

    /**
     * Finds user with that login in database
     *
     * @param login - user's email
     * @return true - if user with that email has been found and false - if it has not
     * @throws DAOException - if request to database has failed
     */
    boolean isLoginExist(String login) throws DAOException;

    /**
     * Finds user with that passport number in database
     *
     * @param passportNum - user's passport number
     * @return true - if user with that passport number has been found and false - if it has not
     * @throws DAOException - if request to database has failed
     */
    boolean isPassportNumExist(String passportNum) throws DAOException;

    /**
     * Finds optional user with that login and password in database
     *
     * @param login    - user's login
     * @param password - user's encrypted password
     * @return Optional<User> -optional user with that login and password has been found in database
     * @throws DAOException - if request to database has failed
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DAOException;

    /**
     * Updates user's password in database
     *
     * @param userId      - user's id
     * @param newPassword - user's new encrypted password
     * @return boolean - password update result
     * @throws DAOException - if request to database has failed
     */
    boolean updatePassword(int userId, String newPassword) throws DAOException;

}
