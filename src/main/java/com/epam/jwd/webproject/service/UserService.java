package com.epam.jwd.webproject.service;

public interface UserService {
    boolean authentication(String login, String password) throws ServiceException;
}
