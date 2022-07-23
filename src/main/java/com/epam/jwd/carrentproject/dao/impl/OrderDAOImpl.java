package com.epam.jwd.carrentproject.dao.impl;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.OrderDAO;
import com.epam.jwd.carrentproject.dao.impl.pool.ConnectionPoolException;
import com.epam.jwd.carrentproject.dao.impl.pool.PoolProvider;
import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.dao.mapper.impl.OrderMapper;
import com.epam.jwd.carrentproject.entity.Order;
import com.epam.jwd.carrentproject.entity.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDAOImpl} class implements the functional of {@link OrderDAO}
 * The class provides access to an underlying database
 *
 * @author Dmitry Murzo
 */
public class OrderDAOImpl implements OrderDAO {
    private static final String INSERT_ORDER =
            "INSERT INTO order_forms (user_id, car_id, pick_up_date, drop_off_date, status) values (?,?,?,?,?)";

    private static final String SELECT_ORDER_BY_USER_ID = "SELECT * FROM order_forms WHERE user_id =? AND (status=? " +
            "OR status=?)";

    private static final String CHANGE_STATUS_ORDER = "UPDATE order_forms SET status=?  WHERE order_id =?";

    private static final String SELECT_ALL_ORDERS = "SELECT * FROM order_forms";

    private static final String SELECT_ORDERS_BY_STATUS = "SELECT * FROM order_forms WHERE status =?";

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orderList;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection(); PreparedStatement statement =
                connection.prepareStatement(SELECT_ALL_ORDERS); ResultSet resultSet = statement.executeQuery()) {

            Mapper<Order> mapper = OrderMapper.getInstance();
            orderList = mapper.retrieve(resultSet);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Error has occurred while finding all orders. ", e);
            throw new DAOException("Error has occurred while finding all orders. ", e);

        }
        return orderList;
    }

    @Override
    public boolean add(Order order) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getCarId());
            preparedStatement.setDate(3, Date.valueOf(order.getPickUpDate()));
            preparedStatement.setDate(4, Date.valueOf(order.getDropOffDate()));
            preparedStatement.setString(5, order.getStatus());

            result = (preparedStatement.executeUpdate() == 1);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Cannot add order to order form's table. ", e);
            throw new DAOException("Cannot add order to order form's table. ", e);
        }

        return result;
    }

    @Override
    public List<Order> findOrdersByUserId(Integer userId) throws DAOException {
        List<Order> orderList;
        Mapper<Order> mapper = OrderMapper.getInstance();
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, OrderStatus.BOOKED_STATUS);
            preparedStatement.setString(3, OrderStatus.CONFIRMED_STATUS);

            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = mapper.retrieve(resultSet);


        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Error has occurred while finding orders by user's id.", e);
            throw new DAOException("Error has occurred while finding orders by user's id.", e);

        }
        return orderList;
    }

    @Override
    public List<Order> findOrdersByStatus(String status) throws DAOException {
        List<Order> orderList;
        Mapper<Order> mapper = OrderMapper.getInstance();
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_STATUS)) {

            preparedStatement.setString(1, status);

            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = mapper.retrieve(resultSet);


        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Error has occurred while finding orders by status.", e);
            throw new DAOException("Error has occurred while finding orders by status.", e);

        }
        return orderList;
    }

    @Override
    public boolean changeStatus(Order order) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS_ORDER)) {

            preparedStatement.setString(1, order.getStatus());
            preparedStatement.setInt(2, order.getOrderId());

            result = (preparedStatement.executeUpdate() == 1);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Cannot change order's status in order_forms table. ", e);
            throw new DAOException("Cannot change order's status in order_forms table. ", e);

        }
        return result;
    }

    @Override
    public boolean inactivate(Order order) throws DAOException {
        throw new UnsupportedOperationException("Deleting order is unsupported");
    }

    @Override
    public boolean update(Order order) throws DAOException {
        throw new UnsupportedOperationException("Updating order is unsupported");
    }

    @Override
    public Optional<Order> findEntityById(Integer entityId) throws DAOException {
        throw new UnsupportedOperationException("Finding order by id is unsupported");
    }


}
