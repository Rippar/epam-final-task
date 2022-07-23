package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * The {@code OrderService} interface defines the business-logic methods for working with the {@link Order} objects
 *
 * @author Dmitry Murzo
 */
public interface OrderService {

    /**
     * Creates new order according to the given order's data
     *
     * @param orderData the order's data
     * @return order's creation result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean createNewOrder(List<Car> availableCars, Map<String, String> orderData) throws ServiceException;

    /**
     * Finds all orders exists in the system
     *
     * @return the list of orders
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<Order> findAllOrders() throws ServiceException;

    /**
     * Check the order's dates
     *
     * @param orderData the order's data with dates
     * @return check order's dates result
     */
    boolean checkTheOrderDates(Map<String, String> orderData);

    /**
     * Gets payment details according to the order's data
     *
     * @param orderData the order's data
     * @return the collection of the payment info
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    Map<String, String> getPaymentDetails(Map<String, String> orderData) throws ServiceException;

    /**
     * Finds all orders by user's id
     *
     * @param userId a user's id
     * @return the list of the user's orders found in the system
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<Order> findAllOrdersByUserId(Integer userId) throws ServiceException;

    /**
     * Finds all orders by its status
     *
     * @param status an order's status
     * @return the list of the orders found in the system
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<Order> findAllOrdersByStatus(String status) throws ServiceException;

    /**
     * Change the order's status
     *
     * @param availableOrders the list of the available orders which could be changed according to the specified status
     * @param orderData       the order's data
     * @return check order's status result
     */
    boolean changeOrderStatus(List<Order> availableOrders, Map<String, String> orderData) throws ServiceException;

}
