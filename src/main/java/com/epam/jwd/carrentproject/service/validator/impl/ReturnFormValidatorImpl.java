package com.epam.jwd.carrentproject.service.validator.impl;

import com.epam.jwd.carrentproject.service.validator.ReturnFormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;

public class ReturnFormValidatorImpl implements ReturnFormValidator {

    private static final String VALUE_REGEX = "^\\d{0,3}([.]\\d{1,2})?$";
    private static final int MAX_DESCRIPTION_LENGTH = 100;

    private static final Logger logger = LogManager.getLogger();

    private static final ReturnFormValidator instance = new ReturnFormValidatorImpl();

    private ReturnFormValidatorImpl() {
    }

    public static ReturnFormValidator getInstance() {
        return instance;
    }


    @Override
    public boolean validateReturnFormData(Map<String, String> returnFormData) {
        String damageDescription = returnFormData.get(DAMAGE_DESCRIPTION_SESSION);
        String billValue = returnFormData.get(BILL_VALUE_SESSION);

        boolean isValid = true;

        if (!(damageDescription.length() <= MAX_DESCRIPTION_LENGTH)) {
            returnFormData.put(WRONG_DESCRIPTION_SESSION, WRONG_DATA_MARKER);
            logger.info("Invalid damage description.");
            isValid = false;
        }

        if(!billValue.isEmpty()) {
            if (!billValue.matches(VALUE_REGEX)) {
                returnFormData.put(WRONG_BILL_VALUE_SESSION, WRONG_DATA_MARKER);
                logger.info("Invalid bill value.");
                isValid = false;
            }
        }


        return isValid;

    }
}
