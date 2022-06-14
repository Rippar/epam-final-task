package com.epam.jwd.webproject.dao.impl.pool;

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
