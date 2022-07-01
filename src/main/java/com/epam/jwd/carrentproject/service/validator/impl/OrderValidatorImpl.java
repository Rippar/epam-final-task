package com.epam.jwd.carrentproject.service.validator.impl;

import com.epam.jwd.carrentproject.service.validator.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;

public class OrderValidatorImpl implements OrderValidator {

    private static final String INTEGER_CHECK = "\\d+";

    private static final String DATE_PATTERN = "MM/d/yyyy";

    private static final Logger logger = LogManager.getLogger();

    private OrderValidatorImpl() {

    }

    private static final OrderValidator instance = new OrderValidatorImpl();

    public static OrderValidator getInstance() {
        return instance;
    }

    @Override
    public boolean validateIdValue(String id) {
        return id.matches(INTEGER_CHECK);
    }

    @Override
    public boolean validateOrderData(Map<String, String> orderData) {

        boolean isValid = false;

        String pickUpDate = orderData.get(ORDER_PICK_UP_DATE_SESSION);
        String dropOffDate = orderData.get(ORDER_DROP_OFF_DATE_SESSION);

        if (pickUpDate.isEmpty() || dropOffDate.isEmpty()) {
            orderData.put(WRONG_DATE_SESSION, WRONG_DATA_MARKER);
            logger.info("Invalid date.");
            return isValid;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        LocalDate startDate = LocalDate.parse(pickUpDate, formatter);
        LocalDate endDate = LocalDate.parse(dropOffDate, formatter);
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isBefore(startDate) && startDate.isBefore(endDate)){
            isValid = true;

        } else {
            orderData.put(WRONG_DATE_SESSION, WRONG_DATA_MARKER);
            logger.info("Invalid date.");
        }

        return isValid;

    }
}
