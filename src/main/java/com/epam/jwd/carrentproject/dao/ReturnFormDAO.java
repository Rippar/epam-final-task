package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.ReturnForm;

import java.util.List;
import java.util.Optional;

public interface ReturnFormDAO extends BaseDAO<Integer, ReturnForm> {

    @Override
    List<ReturnForm> findAll() throws DAOException;

    @Override
    boolean add(ReturnForm returnForm) throws DAOException;

    @Override
    boolean delete(ReturnForm returnForm) throws DAOException;

    @Override
    boolean update(ReturnForm returnForm) throws DAOException;

    @Override
    Optional<ReturnForm> findEntityById(Integer entityId) throws DAOException;
}
