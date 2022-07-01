package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.service.impl.CarServiceImpl;
import com.epam.jwd.carrentproject.service.impl.OrderServiceImpl;
import com.epam.jwd.carrentproject.service.impl.ReturnFormServiceImpl;
import com.epam.jwd.carrentproject.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private UserService userService = new UserServiceImpl();
    private CarService carService = new CarServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    private ReturnFormService returnFormService = new ReturnFormServiceImpl();

    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ReturnFormService getReturnFormService() {
        return returnFormService;
    }

    public void setReturnFormService(ReturnFormService returnFormService) {
        this.returnFormService = returnFormService;
    }
}
