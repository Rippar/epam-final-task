package com.epam.jwd.carrentproject.dao.impl.pool;

public class PoolInitException extends Exception {
    public PoolInitException() {
    }

    public PoolInitException(String message) {
        super(message);
    }

    public PoolInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolInitException(Throwable cause) {
        super(cause);
    }
}
