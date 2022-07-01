package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarDAO extends BaseDAO<Integer, Car>{
    @Override
    List<Car> findAll() throws DAOException;

    @Override
    boolean add(Car car) throws DAOException;

    @Override
    boolean delete(Car car) throws DAOException;

    @Override
    boolean update(Car car) throws DAOException;

    @Override
    Optional<Car> findEntityById(Integer entityId) throws DAOException;

    List<Car> findAllAvailableCars(LocalDate pickUpDate, LocalDate dropOffDate) throws DAOException;
}
