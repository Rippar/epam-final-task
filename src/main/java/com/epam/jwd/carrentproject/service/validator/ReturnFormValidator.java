package com.epam.jwd.carrentproject.service.validator;

import java.math.BigDecimal;
import java.util.Map;

public interface ReturnFormValidator {
    /**
     * Validate return form for create boolean.
     *
     * @param returnFormData the return form data
     * @return the boolean
     */
    boolean validateReturnFormData(Map<String, String> returnFormData);
}
