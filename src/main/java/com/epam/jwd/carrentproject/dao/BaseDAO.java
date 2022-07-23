package com.epam.jwd.carrentproject.dao;

import com.epam.jwd.carrentproject.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents common functional to DAO classes.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 * @author Dmitry Murzo
 */
public interface BaseDAO<K, T extends AbstractEntity> {

    /**
     * Finds all entities from database
     *
     * @return entity list or empty list if table is empty
     * @throws DAOException - if request to database has failed
     */
    List<T> findAll() throws DAOException;

    /**
     * Adds new entity to database
     *
     * @param t - entity extends {@link AbstractEntity}
     * @return true - if entity was created and false - if was not
     * @throws DAOException - if request to database has failed
     */
    boolean add(T t) throws DAOException;

    /**
     * Inactivates entity in database
     *
     * @param t - entity extends {@link AbstractEntity}
     * @return true - if entity was inactivated and false - if was not
     * @throws DAOException - if request to database has failed
     */
    boolean inactivate(T t) throws DAOException;

    /**
     * Updates entity in database
     *
     * @param t - entity extends {@link AbstractEntity}
     * @return true - if entity was updated and false - if was not
     * @throws DAOException - if request to database has failed
     */
    boolean update(T t) throws DAOException;

    /**
     * Finds entity from database by id
     *
     * @param entityId - entity id
     * @return an Optional describing entity, or an empty Optional if entity not found
     * @throws DAOException - if request to database has failed
     */
    Optional<T> findEntityById(K entityId) throws DAOException;

}
