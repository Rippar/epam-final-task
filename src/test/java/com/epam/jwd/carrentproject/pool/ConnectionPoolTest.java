package com.epam.jwd.carrentproject.pool;

import com.epam.jwd.carrentproject.dao.impl.pool.ConnectionPool;
import com.epam.jwd.carrentproject.dao.impl.pool.ConnectionPoolException;
import com.epam.jwd.carrentproject.dao.impl.pool.PoolProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    private ConnectionPool connectionPool;

    @BeforeMethod
    public void initTest() throws ConnectionPoolException {
        connectionPool = PoolProvider.getInstance().getConnectionPool();
        connectionPool.initPoolData();
    }

    @Test
    public void takeConnectionTest() throws ConnectionPoolException {
        Connection con = connectionPool.takeConnection();
        Assert.assertNotNull(con);
    }

    @Test
    public void closeConnectionTest() throws ConnectionPoolException, SQLException {
        Connection con = connectionPool.takeConnection();
        connectionPool.dispose();
        boolean isClosed = con.isClosed();
        Assert.assertTrue(isClosed);


    }

}
