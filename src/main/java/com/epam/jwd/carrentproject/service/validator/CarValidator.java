package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

/**
 * The {@code CarValidator} interface defines the methods to validate car's data
 *
 * @author Dmitry Murzo
 */
public interface CarValidator {

    /**
     * Validates car's id for update.
     *
     * @param id car's id
     * @return the boolean
     */
    boolean validateCarId(String id);

    /**
     * Validates car's data for create and update.
     *
     * @param carData the car's data
     * @return the boolean
     */
    boolean validateCarData(Map<String, String> carData);

}
