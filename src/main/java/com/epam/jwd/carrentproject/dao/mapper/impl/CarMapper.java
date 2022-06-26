package com.epam.jwd.carrentproject.dao.mapper.impl;

import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.jwd.carrentproject.dao.ColumnName.*;
public class CarMapper implements Mapper<Car> {
    private static final CarMapper instance = new CarMapper();

    private CarMapper(){

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CarMapper getInstance() {
        return instance;
    }
    @Override
    public List<Car> retrieve(ResultSet resultSet) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            Car car = new Car.CarBuilder()
                    .withCarId(resultSet.getInt(CAR_ID))
                    .withCarBrand(resultSet.getString(CAR_BRAND))
                    .withCarModel(resultSet.getString(CAR_MODEL))
                    .withCarClass(resultSet.getString(CAR_CLASS))
                    .withCarBody(resultSet.getString(CAR_BODY))
                    .withIsAutoTransmission(resultSet.getBoolean(AUTO_TRANSMISSION))
                    .withAirConditioning(resultSet.getBoolean(AIR_CONDITIONING))
                    .withNumOfDoors(resultSet.getInt(NUM_OF_DOORS))
                    .withNumOfSeats(resultSet.getInt(NUM_OF_SEATS))
                    .withRentalPrice(resultSet.getBigDecimal(RENTAL_PRICE))
                    .withIsActive(resultSet.getBoolean(IS_ACTIVE_CAR))
                    .build();
            cars.add(car);
        }

        return cars;
    }
}
