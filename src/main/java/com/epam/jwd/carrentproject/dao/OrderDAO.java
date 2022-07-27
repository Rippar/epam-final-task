package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.Order;

import java.util.List;

/**
 * The interface extends the functional of {@link BaseDAO}.
 *
 * @author Dmitry Murzo
 */
public interface OrderDAO extends BaseDAO<Integer, Order> {

    /**
     * Finds all orders by user's id in database
     *
     * @param userId - user's id
     * @return List<Order> -the list of all orders by user's id in database
     * @throws DAOException - if request to database has failed
     */
    List<Order> findOrdersByUserId(Integer userId) throws DAOException;

    /**
     * Finds all orders by its status in database
     *
     * @param status - order's status
     * @return List<Order> -the list of all orders by given status in database
     * @throws DAOException - if request to database has failed
     */
    List<Order> findOrdersByStatus(String status) throws DAOException;

    /**
     * Change order's status in database
     *
     * @param order - {@link Order} object
     * @return boolean - change status result
     * @throws DAOException - if request to database has failed
     */
    boolean changeStatus(Order order) throws DAOException;
}
