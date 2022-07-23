package com.epam.jwd.carrentproject.dao.impl.pool;

/**
 * The {@code ConnectionPoolException} class represents a checked exception from
 * {@link com.epam.jwd.carrentproject.dao.impl.pool}
 *
 * @author Dmitry Murzo
 * @see Exception
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
