package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

public interface CarValidator {

    /**
     * Validate car's id for update boolean.
     *
     * @param id car's id
     * @return the boolean
     */
    boolean validateCarId(String id);

    /**
     * Validate car data for create and update boolean.
     *
     * @param carData the car data
     * @return the boolean
     */
    boolean validateCarData(Map<String, String> carData);

}
