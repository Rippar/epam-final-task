package com.epam.jwd.webproject.dao;

public interface UserDAO {
    boolean authentication(String login, String password) throws DAOException;

}
