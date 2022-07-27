package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.Car;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface extends the functional of {@link BaseDAO}.
 *
 * @author Dmitry Murzo
 */
public interface CarDAO extends BaseDAO<Integer, Car> {

    /**
     * Finds all available cars on given dates in database
     *
     * @param pickUpDate - pick up date
     * @param pickUpDate - drop off date
     * @return List<Car> -the list of all available cars on given dates
     * @throws DAOException - if request to database has failed
     */
    List<Car> findAllAvailableCars(LocalDate pickUpDate, LocalDate dropOffDate) throws DAOException;
}
