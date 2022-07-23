package com.epam.jwd.carrentproject.dao.impl;

import com.epam.jwd.carrentproject.dao.CarDAO;
import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.impl.pool.ConnectionPoolException;
import com.epam.jwd.carrentproject.dao.impl.pool.PoolProvider;
import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.dao.mapper.impl.CarMapper;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.entity.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The {@code CarDAOImpl} class implements the functional of {@link CarDAO}
 * The class provides access to an underlying database
 *
 * @author Dmitry Murzo
 */
public class CarDAOImpl implements CarDAO {
    private static final String INSERT_CAR =
            "INSERT INTO cars (car_brand, car_model, car_class, car_body, auto_transmission, air_conditioning, " +
                    "num_of_doors, num_of_seats, rental_price, is_active) values (?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_CAR_INFO = "UPDATE cars SET car_brand=?, car_model=?, car_class=?, car_body=?," +
            " auto_transmission=?, air_conditioning=?, num_of_doors=?, num_of_seats=?, rental_price=?  WHERE car_id=?";

    private static final String SELECT_CAR_BY_ID =
            "SELECT * FROM cars WHERE car_id =?";

    private static final String SELECT_ALL_CARS =
            "SELECT * FROM cars";

    private static final String INACTIVATE_CAR = "UPDATE cars SET is_active = 0 WHERE car_id=?";

    private static final String SELECT_ALL_AVAILABLE_CARS = "SELECT * FROM cars WHERE NOT EXISTS (SELECT order_id " +
            "FROM order_forms WHERE cars.car_id = order_forms.car_id AND order_forms.pick_up_date  <= ? AND " +
            "order_forms.drop_off_date >= ? AND (order_forms.status =? OR order_forms.status=?) OR cars.is_Active =0)";

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Car> findAll() throws DAOException {
        List<Car> carList;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection(); PreparedStatement statement =
                connection.prepareStatement(SELECT_ALL_CARS); ResultSet resultSet = statement.executeQuery()) {

            Mapper<Car> mapper = CarMapper.getInstance();
            carList = mapper.retrieve(resultSet);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Error has occurred while finding cars. ", e);
            throw new DAOException("Error has occurred while finding cars. ", e);

        }
        return carList;
    }

    @Override
    public boolean add(Car car) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR)) {

            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarModel());
            preparedStatement.setString(3, car.getCarClass());
            preparedStatement.setString(4, car.getCarBody());
            preparedStatement.setBoolean(5, car.isAutoTransmission());
            preparedStatement.setBoolean(6, car.isAirConditioning());
            preparedStatement.setInt(7, car.getNumOfDoors());
            preparedStatement.setInt(8, car.getNumOfSeats());
            preparedStatement.setBigDecimal(9, car.getRentalPrice());
            preparedStatement.setBoolean(10, car.isActive());

            result = (preparedStatement.executeUpdate() == 1);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Cannot add car to cars table. ", e);
            throw new DAOException("Cannot add car to cars table. ", e);
        }

        return result;

    }

    @Override
    public boolean inactivate(Car car) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INACTIVATE_CAR)) {

            preparedStatement.setInt(1, car.getCarId());

            result = (preparedStatement.executeUpdate() == 1);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Cannot inactivate car in cars table. ", e);
            throw new DAOException("Cannot inactivate car in cars table. ", e);

        }
        return result;
    }

    @Override
    public boolean update(Car car) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR_INFO)) {

            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarModel());
            preparedStatement.setString(3, car.getCarClass());
            preparedStatement.setString(4, car.getCarBody());
            preparedStatement.setBoolean(5, car.isAutoTransmission());
            preparedStatement.setBoolean(6, car.isAirConditioning());
            preparedStatement.setInt(7, car.getNumOfDoors());
            preparedStatement.setInt(8, car.getNumOfSeats());
            preparedStatement.setBigDecimal(9, car.getRentalPrice());
            preparedStatement.setInt(10, car.getCarId());

            result = (preparedStatement.executeUpdate() == 1);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Cannot update car in cars table. ", e);
            throw new DAOException("Cannot update car in cars table. ", e);
        }

        return result;
    }

    @Override
    public Optional<Car> findEntityById(Integer entityId) throws DAOException {
        Optional<Car> optionalCar;
        Mapper<Car> mapper = CarMapper.getInstance();
        PoolProvider poolProvider = PoolProvider.getInstance();

        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID)) {

            preparedStatement.setInt(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            optionalCar = mapper.retrieve(resultSet).stream().findFirst();


        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Error has occurred while finding car by id.", e);
            throw new DAOException("Error has occurred while finding car by id. ", e);

        }
        return optionalCar;

    }

    @Override
    public List<Car> findAllAvailableCars(LocalDate pickUpDate, LocalDate dropOffDate) throws DAOException {
        List<Car> availableCarList;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection(); PreparedStatement statement =
                connection.prepareStatement(SELECT_ALL_AVAILABLE_CARS)) {

            statement.setDate(1, Date.valueOf(dropOffDate));
            statement.setDate(2, Date.valueOf(pickUpDate));
            statement.setString(3, OrderStatus.BOOKED_STATUS);
            statement.setString(4, OrderStatus.CONFIRMED_STATUS);

            ResultSet resultSet = statement.executeQuery();

            Mapper<Car> mapper = CarMapper.getInstance();
            availableCarList = mapper.retrieve(resultSet);

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error("Error has occurred while finding available cars. ", e);
            throw new DAOException("Error has occurred while finding available cars. ", e);

        }
        return availableCarList;
    }
}
