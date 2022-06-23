package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private UserService userService = new UserServiceImpl();
    //private CarService carService = new CarServiceImpl();
    //private CommonService CommonService = new CommonServiceImpl();
    // и т.д

    private ServiceProvider(){};

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
