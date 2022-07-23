package com.epam.jwd.carrentproject.dao;

/**
 * The {@code DAOException} class represents a checked exception from {@link com.epam.jwd.carrentproject.dao}
 *
 * @author Dmitry Murzo
 * @see Exception
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
