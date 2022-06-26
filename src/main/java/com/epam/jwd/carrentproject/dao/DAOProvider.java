package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.dao.impl.CarDAOImpl;
import com.epam.jwd.carrentproject.dao.impl.UserDAOImpl;

public final class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private UserDAO userDAO = new UserDAOImpl();
    private CarDAO carDAO = new CarDAOImpl();
    //и т.д.

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }
}