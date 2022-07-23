package com.epam.jwd.carrentproject.dao.impl.pool;

/**
 * The {@code DBParameter} class represents the container for database properties names. The class does not contain
 * functionality, only constants.
 *
 * @author Dmitry Murzo
 */
public final class DBParameter {
    private DBParameter(){}
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_POLL_SIZE = "db.poolsize";
}
