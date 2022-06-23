package com.epam.jwd.carrentproject.dao.impl;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.UserDAO;
import com.epam.jwd.carrentproject.dao.impl.pool.ConnectionPoolException;
import com.epam.jwd.carrentproject.dao.impl.pool.PoolProvider;
import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.dao.mapper.impl.UserMapper;
import com.epam.jwd.carrentproject.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    static Logger logger = LogManager.getLogger();
    private final static String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS =
            "SELECT user_id, login, password, first_name, last_name, email, " + "passport_number, is_active, role_id " +
                    "FROM users";
    private static final String INSERT_USER = "INSERT INTO users (login, password, first_name, last_name, email, " +
            "passport_number, is_active, role_id) values (?,?,?,?,?,?,?,?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT user_id FROM users WHERE login = ?";
    private static final String SELECT_USER_BY_PASSPORT = "SELECT user_id FROM users WHERE passport_number = ?";
    private static final String UPDATE_USER_INFO = "UPDATE users SET first_name=?, last_name=?, email=? WHERE user_id=?";
    private static final String INACTIVATE_USER = "UPDATE users SET is_active = 0 WHERE user_id=?";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT user_id, login, password, first_name, " + "last_name, email, passport_number, is_active, role_id " +
                    "FROM users WHERE login=? AND password =? AND is_active=1";

    private static final String SELECT_USER_BY_ID =
            "SELECT user_id, login, password, first_name, last_name, email, " + "passport_number, is_active, role_id " +
            "FROM users WHERE user_id =?";

    private static final String UPDATE_PASSWORD = "UPDATE users SET password =? WHERE user_id =?";

    @Override
    public List<User> findAll() throws DAOException {
        List<User> userList = new ArrayList<>();
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection(); PreparedStatement statement =
                connection.prepareStatement(SELECT_ALL_USERS); ResultSet resultSet = statement.executeQuery()) {

            Mapper<User> mapper = UserMapper.getInstance();
            userList = mapper.retrieve(resultSet);

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while finding users. ", e);
            throw new DAOException("Error has occurred while finding users. ", e);

        }

        return userList;
    }


    @Override
    public boolean add(User user) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassportNum());
            preparedStatement.setBoolean(7, user.isActive());
            preparedStatement.setInt(8, user.getUserRoleId());

            result = (preparedStatement.executeUpdate() == 1 ? true : false);


        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Cannot add user to users table. ", e);
            throw new DAOException("Cannot add user to users table. ", e);

        }
        return result;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INACTIVATE_USER)) {

            preparedStatement.setInt(1, user.getUserId());

            result = (preparedStatement.executeUpdate() == 1 ? true : false);


        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Cannot inactivate user in users table. ", e);
            throw new DAOException("Cannot inactivate user in users table. ", e);

        }
        return result;
    }

    @Override
    public boolean update(User user) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_INFO)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getUserId());

            result = (preparedStatement.executeUpdate() == 1 ? true : false);


        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Cannot update user's info in users table. ", e);
            throw new DAOException("Cannot update user's info in users table. ", e);

        }
        return result;
    }

    @Override
    public Optional<User> findEntityById(Integer entityId) throws DAOException {
        Optional<User> optionalUser;
        Mapper<User> mapper = UserMapper.getInstance();
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            optionalUser = mapper.retrieve(resultSet).stream().findFirst();


        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while finding user by id.", e);
            throw new DAOException("Error has occurred while finding user by id. ", e);

        }
        return optionalUser;

    }

    @Override
    public boolean isLoginExist(String login) throws DAOException {
        boolean isExist;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            isExist = resultSet.next();

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while finding user by login.", e);
            throw new DAOException("Error has occurred while finding user by login.", e);

        }

        return isExist;
    }

    @Override
    public boolean isPassportNumExist(String passportNum) throws DAOException {
        boolean isExist;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PASSPORT)) {

            preparedStatement.setString(1, passportNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while finding user by passport.", e);
            throw new DAOException("Error has occurred while finding user by passport. ", e);

        }

        return isExist;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DAOException {
        Optional<User> optionalUser;
        Mapper<User> mapper = UserMapper.getInstance();
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            optionalUser = mapper.retrieve(resultSet).stream().findFirst();


        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while finding user by login and password. ", e);
            throw new DAOException("Error has occurred while finding user by login and password. ", e);

        }

        return optionalUser;

    }

    @Override
    public boolean updatePassword(int userId, String newPassword) throws DAOException {
        boolean isUpdated;

        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);


            isUpdated = (preparedStatement.executeUpdate() == 1 ? true : false);

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while updating user's password.", e);
            throw new DAOException("Error has occurred while updating user's password.", e);

        }

        return isUpdated;
    }

}
