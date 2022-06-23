package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers() throws ServiceException;
    boolean createNewAccount(Map<String, String> userData) throws ServiceException;
    boolean updateUserPersonalInfo(Map<String, String> userData) throws ServiceException;
    Optional<User> authentication(Map<String, String> userData) throws ServiceException;
    boolean changePassword(Map<String, String> passwordData) throws ServiceException;
    boolean inactivateUser(Map<String, String> userData) throws ServiceException;
}
