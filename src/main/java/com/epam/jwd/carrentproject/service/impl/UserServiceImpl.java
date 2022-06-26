package com.epam.jwd.carrentproject.service.impl;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.DAOProvider;
import com.epam.jwd.carrentproject.dao.UserDAO;
import com.epam.jwd.carrentproject.entity.User;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.UserService;
import com.epam.jwd.carrentproject.service.validator.UserValidator;
import com.epam.jwd.carrentproject.service.validator.impl.UserValidatorImpl;
import com.epam.jwd.carrentproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static final int CUSTOMER_ROLE_ID = 2;

    @Override
    public List<User> findAllUsers() throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        List<User> users;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find all users.", e);
            throw new ServiceException("Unsuccessful attempt to find all users.", e);
        }
        return users;
    }

    @Override
    public boolean createNewAccount(Map<String, String> userData) throws ServiceException {
        boolean isCreated = false;
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        UserValidator userValidator = UserValidatorImpl.getInstance();

        if (!userValidator.validateUserPersonalDataWhenCreate(userData)) {
            return isCreated;
        }
        String login = userData.get(LOGIN_SESSION);
        String password = userData.get(PASSWORD_SESSION);
        String firstName = userData.get(FIRST_NAME_SESSION);
        String lastName = userData.get(LAST_NAME_SESSION);
        String email = userData.get(EMAIL_SESSION);
        String passportNum = userData.get(PASSPORT_SESSION);

        try {
            if (userDAO.isLoginExist(login)) {
                userData.put(WRONG_LOGIN_EXISTS_SESSION, WRONG_DATA_MARKER);
                return isCreated;
            }
            if (userDAO.isLoginExist(passportNum)) {
                userData.put(WRONG_PASSPORT_EXISTS_SESSION, WRONG_DATA_MARKER);
                return isCreated;
            }
            String secretPassword = PasswordEncryptor.md5Apache(password);
            User newUser = new User.UserBuilder()
                    .withLogin(login)
                    .withPassword(secretPassword)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassport(passportNum)
                    .withIsActive(true)
                    .withUserRole(CUSTOMER_ROLE_ID)
                    .build();
            isCreated = userDAO.add(newUser);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to create new account.", e);
            throw new ServiceException("Unsuccessful attempt to create new account.", e);
        }
        return isCreated;

    }

    @Override
    public boolean updateUserPersonalInfo(Map<String, String> userData) throws ServiceException {
        boolean isUpdated = false;
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        Optional<User> optionalUser;

        if (!userValidator.validateUserPersonalDataWhenUpdate(userData)) {
            return isUpdated;
        }

        String login = userData.get(LOGIN_SESSION);
        String password = userData.get(PASSWORD_SESSION);

        try {
            String encryptedPassword = PasswordEncryptor.md5Apache(password);
            optionalUser = userDAO.findUserByLoginAndPassword(login, encryptedPassword);
            if (optionalUser.isEmpty()) {
                logger.info("User " + login + " has not been found in users");
                userData.put(WRONG_PASSWORD_SESSION, WRONG_DATA_MARKER);
                return isUpdated;

            }
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to authenticate user while updating personal info.", e);
            throw new ServiceException("Unsuccessful attempt to authenticate user while updating personal info.", e);
        }

        String firstName = userData.get(FIRST_NAME_SESSION);
        String lastName = userData.get(LAST_NAME_SESSION);
        String email = userData.get(EMAIL_SESSION);


        User tempUser = new User.UserBuilder()
                .withUserId(optionalUser.get().getUserId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .build();

        try {
            isUpdated = userDAO.update(tempUser);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to update user's personal info.", e);
            throw new ServiceException("Unsuccessful attempt to update user's personal info.", e);
        }


        return isUpdated;
    }

    @Override
    public Optional<User> authentication(Map<String, String> userData) throws ServiceException {
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        UserValidator userValidator = UserValidatorImpl.getInstance();
        Optional<User> optionalUser = Optional.empty();
        String login = userData.get(LOGIN_SESSION);
        String password = userData.get(PASSWORD_SESSION);

        if (!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            userData.put(WRONG_LOGIN_OR_PASSWORD_SESSION, WRONG_DATA_MARKER);
            return optionalUser;
        }

        try {
            String encryptedPassword = PasswordEncryptor.md5Apache(password);
            optionalUser = userDAO.findUserByLoginAndPassword(login, encryptedPassword);
            if (optionalUser.isEmpty()) {
                logger.info("User " + login + " has not found in users");
                userData.put(NOT_FOUND_SESSION, WRONG_DATA_MARKER);

            }
        } catch (DAOException e) {
            logger.error("Unsuccessful authentication attempt.", e);
            throw new ServiceException("Unsuccessful authentication attempt. ", e);
        }

        return optionalUser;

    }

    @Override
    public boolean changePassword(Map<String, String> passwordData) throws ServiceException {
        boolean isChanged = false;
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        int userId = Integer.parseInt(passwordData.get(USER_ID_SESSION));
        String login = passwordData.get(LOGIN_SESSION);
        String oldPassword = passwordData.get(PASSWORD_SESSION);
        String newPassword = passwordData.get(NEW_PASSWORD_SESSION);
        String newRepeatPassword = passwordData.get(REPEAT_NEW_PASSWORD_SESSION);
        UserValidator validator = UserValidatorImpl.getInstance();
        Optional<User> optionalUser;

        if (!newPassword.equals(newRepeatPassword)) {
            logger.info("Invalid new repeated password.");
            passwordData.put(WRONG_REPEATED_NEW_PASSWORD_SESSION, WRONG_DATA_MARKER);
            return isChanged;
        }

        if (!validator.validatePassword(newPassword) || !validator.validatePassword(oldPassword)) {
            logger.info("Invalid password.");
            passwordData.put(WRONG_PASSWORD_SESSION, WRONG_DATA_MARKER);
            return isChanged;
        }

        String oldSecretPassword = PasswordEncryptor.md5Apache(oldPassword);

        try {
            optionalUser = userDAO.findUserByLoginAndPassword(login, oldSecretPassword);
            if (optionalUser.isEmpty()) {
                logger.info("Wrong password.");
                passwordData.put(WRONG_PASSWORD_SESSION, WRONG_DATA_MARKER);
                return isChanged;
            }
            String newSecretPassword = PasswordEncryptor.md5Apache(newPassword);
            isChanged = userDAO.updatePassword(userId, newSecretPassword);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to change password.", e);
            throw new ServiceException("Unsuccessful attempt to change password.", e);
        }

        return isChanged;
    }

    @Override
    public boolean inactivateUser(Map<String, String> userData) throws ServiceException {
        boolean isInactivate = false;
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        Optional<User> optionalUser;
        UserValidator validator = UserValidatorImpl.getInstance();

        if (!validator.validateUserId(userData.get(USER_ID_TO_INACTIVATE_SESSION))) {
            logger.info("User with Id " + userData.get(USER_ID_TO_INACTIVATE_SESSION) + " has not found in users");
            userData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isInactivate;
        }

        int userIdToInactivate = Integer.parseInt(userData.get(USER_ID_TO_INACTIVATE_SESSION));

        try {
            optionalUser = userDAO.findEntityById(userIdToInactivate);
            if (optionalUser.isEmpty()) {
                logger.info("User with Id " + userIdToInactivate + " has not found in users");
                userData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
                return isInactivate;

            }
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find user to inactivate it.", e);
            throw new ServiceException("Unsuccessful attempt to find user to inactivate it.", e);
        }

        User tempUser = new User.UserBuilder()
                .withUserId(optionalUser.get().getUserId())
                .build();

        try {
            isInactivate = userDAO.delete(tempUser);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to inactivate user.", e);
            throw new ServiceException("Unsuccessful attempt to inactivate user.", e);
        }

        return isInactivate;
    }

}
