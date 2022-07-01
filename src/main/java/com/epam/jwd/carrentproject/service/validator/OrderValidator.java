package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

public interface OrderValidator {

    /**
     * Validate car's id to order it.
     *
     * @param id car's id
     * @return the boolean
     */
    boolean validateIdValue(String id);

    /**
     * Validate order data for create and update boolean.
     *
     * @param orderData the order data
     * @return the boolean
     */
    boolean validateOrderData(Map<String, String> orderData);
}
