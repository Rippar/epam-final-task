package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.entity.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CarService {
    boolean createNewCar(Map<String, String> carData) throws ServiceException;
    boolean updateCar(Map<String, String> carData) throws ServiceException;
    List<Car> findAllCars() throws ServiceException;
    boolean inactivateCar(Map<String, String> carData) throws ServiceException;
    List<Car> findAllAvailableCars(Map<String, String> orderData) throws ServiceException;
}
