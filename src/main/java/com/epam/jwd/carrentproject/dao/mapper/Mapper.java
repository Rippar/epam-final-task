package com.epam.jwd.carrentproject.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The {@code Mapper} interface defines an abstract method which retrieves the list of entities from a database
 * result set
 *
 * @param <T> the type parameter
 * @author Dmitry Murzo
 */

public interface Mapper<T> {
    /**
     * Retrieves the list of entities from the result set.
     *
     * @param resultSet the result set
     * @return the list
     * @throws SQLException the sql exception
     */
    List<T> retrieve(ResultSet resultSet) throws SQLException;

}
