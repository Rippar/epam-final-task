package com.epam.jwd.carrentproject.service.impl;

import com.epam.jwd.carrentproject.dao.CarDAO;
import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.DAOProvider;
import com.epam.jwd.carrentproject.dao.OrderDAO;
import com.epam.jwd.carrentproject.dao.UserDAO;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.entity.Order;
import com.epam.jwd.carrentproject.entity.OrderStatus;
import com.epam.jwd.carrentproject.entity.User;
import com.epam.jwd.carrentproject.service.OrderService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.validator.OrderValidator;
import com.epam.jwd.carrentproject.service.validator.impl.OrderValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;
import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

public class OrderServiceImpl implements OrderService {

    private static final String DATE_PATTERN = "MM/d/yyyy";

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean createNewOrder(List<Car> availableCars, Map<String, String> orderData) throws ServiceException {
        boolean isCreated = false;
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        OrderValidator orderValidator = OrderValidatorImpl.getInstance();

        int userId = Integer.parseInt(orderData.get(USER_ID_SESSION));

        if (!orderValidator.validateIdValue(orderData.get(CAR_ID_TO_ORDER_SESSION))) {
            logger.info("Incorrect car's id to order: " + orderData.get(CAR_ID_TO_ORDER_SESSION));
            orderData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isCreated;
        }

        int carId = Integer.parseInt(orderData.get(CAR_ID_TO_ORDER_SESSION));

        if (availableCars.stream().noneMatch(car -> car.getCarId() == carId)) {
            logger.info("Incorrect available car's id to order: " + carId);
            orderData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isCreated;
        }


        LocalDate pickUpDate = LocalDate.parse(orderData.get(ORDER_PICK_UP_DATE_SESSION), formatter);
        LocalDate dropOffDate = LocalDate.parse(orderData.get(ORDER_DROP_OFF_DATE_SESSION), formatter);

        Order order = new Order.OrderBuilder()
                .withUserId(userId)
                .withCarId(carId)
                .withPickUpDate(pickUpDate)
                .withDropUpDate(dropOffDate)
                .withStatus(OrderStatus.BOOKED_STATUS)
                .build();

        try {
            isCreated = orderDAO.add(order);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to create new order.", e);
            throw new ServiceException("Unsuccessful attempt to create new order.", e);
        }


        return isCreated;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orders;

        try {
            orders = orderDAO.findAll();
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find all orders.", e);
            throw new ServiceException("Unsuccessful attempt to find all orders.", e);
        }

        return orders;
    }

    @Override
    public boolean checkTheOrderDates(Map<String, String> orderData) {
        OrderValidator orderValidator = OrderValidatorImpl.getInstance();
        return orderValidator.validateOrderData(orderData);

    }

    @Override
    public Map<String, String> getPaymentDetails(Map<String, String> orderData) throws ServiceException {
        Map<String, String> paymentDetails = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        CarDAO carDAO = DAOProvider.getInstance().getCarDAO();
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

        int carId = Integer.parseInt(orderData.get(CAR_ID_TO_ORDER_SESSION));
        int userId = Integer.parseInt(orderData.get(USER_ID_SESSION));

        Optional<Car> car;
        Optional<User> user;

        try {
            car = carDAO.findEntityById(carId);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find the car.", e);
            throw new ServiceException("Unsuccessful attempt to find the car.", e);
        }

        try {
            user = userDAO.findEntityById(userId);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find the user.", e);
            throw new ServiceException("Unsuccessful attempt to find the user.", e);
        }

        LocalDate pickUpDate = LocalDate.parse(orderData.get(ORDER_PICK_UP_DATE_SESSION), formatter);
        LocalDate dropOffDate = LocalDate.parse(orderData.get(ORDER_DROP_OFF_DATE_SESSION), formatter);

        int dateOfRent = (int) (dropOffDate.toEpochDay() - pickUpDate.toEpochDay());

        BigDecimal paymentSum = (car.get().getRentalPrice()).multiply(BigDecimal.valueOf(++dateOfRent));

        if (user.isPresent()) {
            paymentDetails.put(PAYMENT_NAME_SESSION, user.get().getFirstName());
            paymentDetails.put(PAYMENT_SURNAME_SESSION, user.get().getLastName());
            paymentDetails.put(PAYMENT_PASSPORT_NUMBER_SESSION, user.get().getPassportNum());
            paymentDetails.put(PAYMENT_EMAIL_SESSION, user.get().getEmail());
        }

        if (car.isPresent()) {
            paymentDetails.put(PAYMENT_SUM_SESSION, paymentSum.toPlainString());
            paymentDetails.put(PAYMENT_CAR_BRAND_SESSION, car.get().getCarBrand());
            paymentDetails.put(PAYMENT_CAR_MODEL_SESSION, car.get().getCarModel());
            paymentDetails.put(PAYMENT_CAR_CLASS_SESSION, car.get().getCarClass());
        }

        paymentDetails.put(ORDER_PICK_UP_DATE_SESSION, orderData.get(ORDER_PICK_UP_DATE_SESSION));
        paymentDetails.put(ORDER_DROP_OFF_DATE_SESSION, orderData.get(ORDER_DROP_OFF_DATE_SESSION));

        return paymentDetails;
    }

    @Override
    public List<Order> findAllOrdersByUserId(Integer userId) throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orderList;

        try {
            orderList = orderDAO.findOrdersByUserId(userId);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find the user's orders.", e);
            throw new ServiceException("Unsuccessful attempt to find the user's orders.", e);
        }

        return orderList;
    }

    @Override
    public List<Order> findAllOrdersByStatus(String status) throws ServiceException {
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();
        List<Order> orderList;

        try {
            orderList = orderDAO.findOrdersByStatus(status);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find the orders by status.", e);
            throw new ServiceException("Unsuccessful attempt to find the orders by status.", e);
        }

        return orderList;
    }

    @Override
    public boolean changeOrderStatus(List<Order> availableOrders, Map<String, String> orderData) throws ServiceException {
        boolean isChanged = false;
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAO();

        OrderValidator orderValidator = OrderValidatorImpl.getInstance();

        if (!orderValidator.validateIdValue(orderData.get(ORDER_ID_SESSION))) {
            logger.info("Incorrect order's id: " + orderData.get(ORDER_ID_SESSION));
            orderData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isChanged;
        }

        int orderId = Integer.parseInt(orderData.get(ORDER_ID_SESSION));

        if (availableOrders.stream().noneMatch(order -> order.getOrderId() == orderId)) {
            logger.info("Incorrect available order's id: " + orderId);
            orderData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isChanged;
        }

//        if(orderData.get(REASON_FOR_CANCELING_SESSION) != null) {
//            to do the option to send the canceling reason to user's email
//        }


        Order order = new Order.OrderBuilder()
                .withOrderId(orderId)
                .withStatus(orderData.get(ORDER_STATUS_SESSION))
                .build();

        try {
            isChanged = orderDAO.changeStatus(order);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to change order's status.", e);
            throw new ServiceException("Unsuccessful attempt to change order's status.", e);
        }

        return isChanged;
    }

}
