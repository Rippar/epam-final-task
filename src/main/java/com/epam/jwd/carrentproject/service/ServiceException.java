package com.epam.jwd.carrentproject.service;

/**
 * The {@code ServiceException} class represents a checked exception from {@link com.epam.jwd.carrentproject.service}
 *
 * @author Dmitry Murzo
 * @see Exception
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
