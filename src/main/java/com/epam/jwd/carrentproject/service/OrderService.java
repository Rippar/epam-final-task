package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean createNewOrder(List<Car> availableCars, Map<String, String> orderData) throws ServiceException;
    List<Order> findAllOrders() throws ServiceException;
    boolean checkTheOrderDates(Map<String, String> orderData);
    Map<String, String> getPaymentDetails(Map<String, String> orderData) throws ServiceException;
    List<Order> findAllOrdersByUserId(Integer userId) throws ServiceException;
    List<Order> findAllOrdersByStatus(String status) throws ServiceException;
    boolean changeOrderStatus(List<Order> availableOrders, Map<String, String> orderData) throws ServiceException;

}
