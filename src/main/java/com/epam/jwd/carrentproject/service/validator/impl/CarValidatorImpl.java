package com.epam.jwd.carrentproject.service.validator.impl;

import com.epam.jwd.carrentproject.service.validator.CarValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;

/**
 * The {@code CarValidatorImpl} class implements the functional of {@link CarValidator}
 * The class implements the methods to validate car's data
 *
 * @author Dmitry Murzo
 */
public class CarValidatorImpl implements CarValidator {

    private static final String CAR_BRAND_REGEX = "[а-яё\\p{Lower}]{1,15}";
    private static final String CAR_MODEL_REGEX = "^[\\w\\sА-я.\\-]{1,20}$";
    private static final String PRICE_REGEX = "^\\d{0,3}([.]\\d{1,2})?$";
    private static final String BOOLEAN_CHECK = "[01]";
    private static final String NUM_OF_DOORS_CHECK = "[2345]";
    private static final String NUM_OF_SEATS_CHECK = "[245678]";
    private static final String INTEGER_CHECK = "\\d+";
    private static final String PREMIUM_CLASS = "премиум";
    private static final String STANDART_CLASS = "стандарт";
    private static final String BUSINESS_CLASS = "бизнес";
    private static final String COMPACT_CLASS = "компакт";
    private static final String ECONOMY_CLASS = "эконом";
    private static final String SALOON_BODY = "седан";
    private static final String WAGON_BODY = "универсал";
    private static final String SUV_BODY = "кроссовер";
    private static final String MINIVAN_BODY = "минивэн";
    private static final String HATCHBACK_BODY = "хэтчбек";
    private static final String COUPE_BODY = "купе";
    private static final String CONVERTIBLE_BODY = "кабриолет";

    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public boolean validateCarId(String id) {
        return id.matches(INTEGER_CHECK);
    }

    @Override
    public boolean validateCarData(Map<String, String> carData) {
        String carBrand = carData.get(CAR_BRAND_SESSION);
        String carModel = carData.get(CAR_MODEL_SESSION);
        String carClass = carData.get(CAR_CLASS_SESSION);
        String carBody = carData.get(CAR_BODY_SESSION);
        String autoTransmission = carData.get(AUTO_TRANSMISSION_SESSION);
        String airConditioning = carData.get(AIR_CONDITIONING_SESSION);
        String numOfDoors = carData.get(NUM_OF_DOORS_SESSION);
        String numOfSeats = carData.get(NUM_OF_SEATS_SESSION);
        String rentalPrice = carData.get(RENTAL_PRICE_SESSION);

        List<String> carClasses = Arrays.asList(PREMIUM_CLASS, STANDART_CLASS, BUSINESS_CLASS,
                COMPACT_CLASS, ECONOMY_CLASS);

        List<String> carBodies = Arrays.asList(SALOON_BODY, WAGON_BODY, SUV_BODY, MINIVAN_BODY,
                HATCHBACK_BODY, COUPE_BODY, CONVERTIBLE_BODY);

        boolean isValid = true;

        if (carBrand == null || !carBrand.matches(CAR_BRAND_REGEX)) {
            carData.put(WRONG_CAR_BRAND_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid car brand.");
            isValid = false;
        }

        if (carModel == null || !carModel.matches(CAR_MODEL_REGEX)) {
            carData.put(WRONG_CAR_MODEL_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid car model.");
            isValid = false;
        }

        if (carClass == null || carClasses.stream().noneMatch(element -> element.contains(carClass))) {

            carData.put(WRONG_CAR_CLASS_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid car class.");
            isValid = false;
        }

        if (carBody == null || carBodies.stream().noneMatch(element -> element.contains(carBody))) {

            carData.put(WRONG_CAR_BODY_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid car body.");
            isValid = false;
        }

        if (autoTransmission == null || !autoTransmission.matches(BOOLEAN_CHECK)) {
            carData.put(WRONG_AUTO_TRANSMISSION_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid auto transmission value.");
            isValid = false;
        }

        if (airConditioning == null || !airConditioning.matches(BOOLEAN_CHECK)) {
            carData.put(WRONG_AIR_CONDITIONING_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid air conditioning value.");
            isValid = false;
        }

        if (numOfDoors == null || !numOfDoors.matches(NUM_OF_DOORS_CHECK)) {
            carData.put(WRONG_NUM_OF_DOORS_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid num of doors value.");
            isValid = false;
        }

        if (numOfSeats == null || !numOfSeats.matches(NUM_OF_SEATS_CHECK)) {
            carData.put(WRONG_NUM_OF_SEATS_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid num of seats value.");
            isValid = false;
        }

        if (rentalPrice == null || !rentalPrice.matches(PRICE_REGEX)) {
            carData.put(WRONG_PRICE_SESSION, WRONG_DATA_MARKER);
            LOGGER.info("Invalid price value.");
            isValid = false;
        }


        return isValid;
    }
}
