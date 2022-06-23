package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

/**
 * The interface represent common functional to DAO classes.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface BaseDAO <K, T extends AbstractEntity> {
    /**
     * Find all entities from database
     *
     * @return entity list or empty list if table is empty
     * @throws DAOException - if request from database was failed
     */
    List<T> findAll() throws DAOException;

    /**
     * Add new entity to database
     *
     *  @param t - entity extends {@link AbstractEntity}
     *  @return true - if entity was created and false - if was not
     *  @throws DAOException - if request from database was failed
     */
    boolean add(T t) throws DAOException;

    /**
     * Delete entity from database.
     *
     * @param t - entity extends {@link AbstractEntity}
     * @return true - if entity was deleted and false - if was not
     * @throws DAOException - if request from database was failed
     */
    boolean delete(T t) throws DAOException;

    /**
     * Update entity in database
     * @param t - entity extends {@link AbstractEntity}
     * @return true - if entity was updated and false - if was not
     * @throws DAOException - if request from database was failed
     */
    boolean update(T t) throws DAOException;

    /**
     * Find entity from database by id
     * @param entityId - entity id
     * @return an Optional describing entity, or an empty Optional if entity not found
     * @throws DAOException - if request from database was failed
     */
    Optional<T> findEntityById(K entityId) throws DAOException;

}
