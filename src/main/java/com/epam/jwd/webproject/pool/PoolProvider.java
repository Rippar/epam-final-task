package com.epam.jwd.webproject.pool;

public class PoolProvider {

    private final static PoolProvider instance = new PoolProvider();

    private ConnectionPool connectionPool = new ConnectionPool();

    private PoolProvider() {
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            //заменить на нормальный Exception (например даоэксепшн) или лог?
            throw new RuntimeException(e);
        }
    }

    public static PoolProvider getInstance(){
        return instance;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
