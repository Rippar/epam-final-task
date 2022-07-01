package com.epam.jwd.carrentproject.dao.impl;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.ReturnFormDAO;
import com.epam.jwd.carrentproject.dao.impl.pool.ConnectionPoolException;
import com.epam.jwd.carrentproject.dao.impl.pool.PoolProvider;
import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.dao.mapper.impl.ReturnFormMapper;
import com.epam.jwd.carrentproject.entity.ReturnForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReturnFormDAOImpl implements ReturnFormDAO {
    private static final String INSERT_RETURN_FORM =
            "INSERT INTO return_forms (order_id, date_of_return, damage_description, bill_value) values (?,?,?,?)";

    private static final String SELECT_ALL_RETURN_FORMS = "SELECT * FROM return_forms";

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<ReturnForm> findAll() throws DAOException {
        List<ReturnForm> returnForms;

        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection(); PreparedStatement statement =
                connection.prepareStatement(SELECT_ALL_RETURN_FORMS); ResultSet resultSet = statement.executeQuery()) {

            Mapper<ReturnForm> mapper = ReturnFormMapper.getInstance();
            returnForms = mapper.retrieve(resultSet);

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Error has occurred while finding return forms. ", e);
            throw new DAOException("Error has occurred while finding return forms. ", e);

        }
        return returnForms;
    }

    @Override
    public boolean add(ReturnForm returnForm) throws DAOException {
        boolean result;
        PoolProvider poolProvider = PoolProvider.getInstance();
        try (Connection connection = poolProvider.getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RETURN_FORM)) {

            preparedStatement.setInt(1, returnForm.getOrderId());
            preparedStatement.setDate(2, Date.valueOf(returnForm.getDateOfReturn()));
            preparedStatement.setString(3, returnForm.getDamageDescription());
            preparedStatement.setBigDecimal(4, returnForm.getBillValue());

            result = (preparedStatement.executeUpdate() == 1);

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Cannot add return form to return form's table. ", e);
            throw new DAOException("Cannot add return form to return form's table. ", e);
        }

        return result;
    }

    @Override
    public boolean delete(ReturnForm returnForm) throws DAOException {
        throw new UnsupportedOperationException("Deleting return form is unsupported");
    }

    @Override
    public boolean update(ReturnForm returnForm) throws DAOException {
        throw new UnsupportedOperationException("Updating return form is unsupported");
    }

    @Override
    public Optional<ReturnForm> findEntityById(Integer entityId) throws DAOException {
        throw new UnsupportedOperationException("Finding return form by id is unsupported");
    }
}
