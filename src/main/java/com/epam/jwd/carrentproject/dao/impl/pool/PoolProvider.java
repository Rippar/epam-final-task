package com.epam.jwd.carrentproject.dao.impl.pool;

/**
 * The {@code PoolProvider} class uses singleton pattern to control the number of ConnectionPool objects created.
 * Initializes the connection pool the first time this class is accessed
 *
 * @author Dmitry Murzo
 */
public class PoolProvider {

    private final static PoolProvider INSTANCE;

    static {
        try {
            INSTANCE = new PoolProvider();
        } catch (PoolInitException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConnectionPool connectionPool = new ConnectionPool();

    private PoolProvider() throws PoolInitException {
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new PoolInitException();
        }
    }

    public static PoolProvider getInstance() {
        return INSTANCE;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

}
