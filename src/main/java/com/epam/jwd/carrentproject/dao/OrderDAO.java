package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO extends BaseDAO<Integer, Order> {
    @Override
    List<Order> findAll() throws DAOException;

    @Override
    boolean add(Order order) throws DAOException;

    @Override
    boolean delete(Order order) throws DAOException;

    @Override
    boolean update(Order order) throws DAOException;

    @Override
    Optional<Order> findEntityById(Integer entityId) throws DAOException;
    List<Order> findOrdersByUserId(Integer userId) throws DAOException;
    List<Order> findOrdersByStatus(String status) throws DAOException;

    boolean changeStatus(Order order) throws DAOException;
}
