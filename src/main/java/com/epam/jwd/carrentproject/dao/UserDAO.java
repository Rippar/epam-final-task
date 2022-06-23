package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.User;

import java.util.Optional;

public interface UserDAO extends  BaseDAO<Integer, User>{

    /**
     * Find user with that login in database
     * @param login - user's email
     * @return true - if user with that email has been found and false - if it has not
     * @throws DAOException - if request from database has failed
     */
    boolean isLoginExist(String login) throws DAOException;

    /**
     * Find user with that passport number in database
     * @param passportNum - user's passport number
     * @return true - if user with that passport number has been found and false - if it has not
     * @throws DAOException - if request from database has failed
     */
    boolean isPassportNumExist(String passportNum) throws DAOException;

    /**
     * Find optional user with that login and password in database
     * @param login - user's login
     * @param password - user's encrypted password
     * @return Optional<User> -optional user with that login and password has been found in database
     * @throws DAOException - if request from database has failed
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DAOException;

    boolean updatePassword(int userId, String newPassword) throws DAOException;

}
