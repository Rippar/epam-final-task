package com.epam.jwd.carrentproject.dao.impl.pool;

public class PoolProvider {

    private final static PoolProvider instance;

    static {
        try {
            instance = new PoolProvider();
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
        //lock
        return instance;
        //unlock
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
