package com.epam.jwd.carrentproject.dao.mapper;

import com.epam.jwd.carrentproject.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @project car rent project
 * @author Dmitry Murzo
 * The interface Mapper.
 *
 * @param <T> the type parameter
 */

public interface Mapper <T extends AbstractEntity>{
    /**
     * Retrieve list.
     *
     * @param resultSet the result set
     * @return the list
     * @throws SQLException the sql exception
     */
    List<T> retrieve(ResultSet resultSet) throws SQLException;

}
