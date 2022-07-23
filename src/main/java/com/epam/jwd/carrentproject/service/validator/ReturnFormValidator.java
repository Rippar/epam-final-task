package com.epam.jwd.carrentproject.service.validator;

import java.util.Map;

/**
 * The {@code ReturnFormValidator} interface defines the methods to validate return form's data
 *
 * @author Dmitry Murzo
 */
public interface ReturnFormValidator {
    /**
     * Validates return form's for create
     *
     * @param returnFormData the return form's data
     * @return the boolean
     */
    boolean validateReturnFormData(Map<String, String> returnFormData);
}
