package com.epam.jwd.carrentproject.service.impl;

import com.epam.jwd.carrentproject.dao.CarDAO;
import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.DAOProvider;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.service.CarService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.validator.CarValidator;
import com.epam.jwd.carrentproject.service.validator.impl.CarValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.WRONG_DATA_MARKER;
import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;


public class CarServiceImpl implements CarService {

    static Logger logger = LogManager.getLogger();
    private static final String TRUE_VALUE = "1";

    @Override
    public boolean createNewCar(Map<String, String> carData) throws ServiceException {
        boolean isCreated = false;
        CarDAO carDAO = DAOProvider.getInstance().getCarDAO();
        CarValidator carValidator = CarValidatorImpl.getInstance();

        if (!carValidator.validateCarData(carData)) {
            return isCreated;
        }

        String carBrand = carData.get(CAR_BRAND_SESSION);
        String carModel = carData.get(CAR_MODEL_SESSION);
        String carClass = carData.get(CAR_CLASS_SESSION);
        String carBody = carData.get(CAR_BODY_SESSION);
        boolean autoTransmission = (carData.get(AUTO_TRANSMISSION_SESSION).equals(TRUE_VALUE));
        boolean airConditioning = (carData.get(AIR_CONDITIONING_SESSION).equals(TRUE_VALUE));
        int numOfDoors = Integer.parseInt(carData.get(NUM_OF_DOORS_SESSION));
        int numOfSeats = Integer.parseInt(carData.get(NUM_OF_SEATS_SESSION));
        BigDecimal rentalPrice = BigDecimal.valueOf(Double.parseDouble(carData.get(RENTAL_PRICE_SESSION)));

        Car newCar = new Car.CarBuilder()
                .withCarBrand(carBrand)
                .withCarModel(carModel)
                .withCarClass(carClass)
                .withCarBody(carBody)
                .withIsAutoTransmission(autoTransmission)
                .withAirConditioning(airConditioning)
                .withNumOfDoors(numOfDoors)
                .withNumOfSeats(numOfSeats)
                .withRentalPrice(rentalPrice)
                .withIsActive(true)
                .build();

        try {
            isCreated = carDAO.add(newCar);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to create new car.", e);
            throw new ServiceException("Unsuccessful attempt to create new car.", e);
        }

        return isCreated;

    }

    @Override
    public boolean updateCar(Map<String, String> carData) throws ServiceException {
        boolean isUpdated = false;
        CarDAO carDAO = DAOProvider.getInstance().getCarDAO();
        CarValidator carValidator = CarValidatorImpl.getInstance();
        Optional<Car> optionalCar;

        if (!carValidator.validateCarId(carData.get(CAR_ID_SESSION))) {
            logger.info("Car with id " + carData.get(CAR_ID_SESSION) + " has not been found in cars");
            carData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isUpdated;
        }

        int carId = Integer.parseInt(carData.get(CAR_ID_SESSION));

        try {
            optionalCar = carDAO.findEntityById(carId);
            if (optionalCar.isEmpty()) {
                logger.info("Car with id " + carId + " has not been found in cars");
                carData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
                return isUpdated;

            }
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find the car while updating car's info.", e);
            throw new ServiceException("Unsuccessful attempt to find the car while updating car's info.", e);
        }

        if (!carValidator.validateCarData(carData)) {
            return isUpdated;
        }

        String carBrand = carData.get(CAR_BRAND_SESSION);
        String carModel = carData.get(CAR_MODEL_SESSION);
        String carClass = carData.get(CAR_CLASS_SESSION);
        String carBody = carData.get(CAR_BODY_SESSION);
        boolean autoTransmission = (carData.get(AUTO_TRANSMISSION_SESSION).equals(TRUE_VALUE));
        boolean airConditioning = (carData.get(AIR_CONDITIONING_SESSION).equals(TRUE_VALUE));
        int numOfDoors = Integer.parseInt(carData.get(NUM_OF_DOORS_SESSION));
        int numOfSeats = Integer.parseInt(carData.get(NUM_OF_SEATS_SESSION));
        BigDecimal rentalPrice = BigDecimal.valueOf(Double.parseDouble(carData.get(RENTAL_PRICE_SESSION)));

        Car updatedCar = new Car.CarBuilder()
                .withCarId(carId)
                .withCarBrand(carBrand)
                .withCarModel(carModel)
                .withCarClass(carClass)
                .withCarBody(carBody)
                .withIsAutoTransmission(autoTransmission)
                .withAirConditioning(airConditioning)
                .withNumOfDoors(numOfDoors)
                .withNumOfSeats(numOfSeats)
                .withRentalPrice(rentalPrice)
                .withIsActive(true)
                .build();

        try {
            isUpdated = carDAO.update(updatedCar);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to update car's info.", e);
            throw new ServiceException("Unsuccessful attempt to update car's info.", e);
        }

        return isUpdated;
    }

    @Override
    public List<Car> findAllCars() throws ServiceException {
        CarDAO carDAO = DAOProvider.getInstance().getCarDAO();
        List<Car> cars;
        try {
            cars = carDAO.findAll();

        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find all cars.", e);
            throw new ServiceException("Unsuccessful attempt to find all cars.", e);
        }

        return cars;
    }

    @Override
    public boolean inactivateCar(Map<String, String> carData) throws ServiceException {
        boolean isInactivate = false;
        CarDAO carDAO = DAOProvider.getInstance().getCarDAO();
        Optional<Car> optionalCar;
        CarValidator carValidator = CarValidatorImpl.getInstance();

        if (!carValidator.validateCarId(carData.get(CAR_ID_TO_INACTIVATE_SESSION))) {
            logger.info("Car with id " + carData.get(CAR_ID_TO_INACTIVATE_SESSION) + " has not been found in cars");
            carData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
            return isInactivate;
        }

        int carId = Integer.parseInt(carData.get(CAR_ID_TO_INACTIVATE_SESSION));

        try {
            optionalCar = carDAO.findEntityById(carId);
            if (optionalCar.isEmpty()) {
                logger.info("Car with id " + carId + " has not been found in cars");
                carData.put(WRONG_ID_SESSION, WRONG_DATA_MARKER);
                return isInactivate;

            }
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to find the car to inactivate it.", e);
            throw new ServiceException("Unsuccessful attempt to find the car to inactivate it.", e);
        }

        Car tempCar = new Car.CarBuilder()
                .withCarId(carId)
                .build();

        try {
            isInactivate = carDAO.delete(tempCar);
        } catch (DAOException e) {
            logger.error("Unsuccessful attempt to inactivate car.", e);
            throw new ServiceException("Unsuccessful attempt to inactivate car.", e);
        }


        return isInactivate;

    }
}
