package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

public interface OrderValidator {

    /**
     * Validates car's id to order it.
     *
     * @param id car's id
     * @return the boolean
     */
    boolean validateIdValue(String id);

    /**
     * Validate order's data for create and update
     *
     * @param orderData the order's data
     * @return the boolean
     */
    boolean validateOrderData(Map<String, String> orderData);
}
