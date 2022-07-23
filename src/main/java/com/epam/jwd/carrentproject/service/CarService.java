package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.entity.Car;

import java.util.List;
import java.util.Map;

/**
 * The {@code CarService} interface defines the business-logic methods for working with the {@link Car} objects
 *
 * @author Dmitry Murzo
 */
public interface CarService {

    /**
     * Creates new car according to the given car's data
     *
     * @param carData the car's data
     * @return car's creation result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean createNewCar(Map<String, String> carData) throws ServiceException;

    /**
     * Updates new car according to the given car's data
     *
     * @param carData the car's data
     * @return car's update result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean updateCar(Map<String, String> carData) throws ServiceException;

    /**
     * Finds all cars exists in the system
     *
     * @return the list of cars
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<Car> findAllCars() throws ServiceException;

    /**
     * Inactivate the specific car in the system
     *
     * @param carData the car's data
     * @return inactivate car result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean inactivateCar(Map<String, String> carData) throws ServiceException;

    /**
     * Finds all available cars according to the order's data
     *
     * @param orderData the order's data
     * @return the list of available cars
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<Car> findAllAvailableCars(Map<String, String> orderData) throws ServiceException;
}
