package com.epam.jwd.carrentproject.dao.impl.pool;

import java.util.ResourceBundle;

/**
 * The {@code DBResourceManager} class uses singleton pattern to control the number of DBResourceBundle objects created.
 *
 * @author Dmitry Murzo
 */
public class DBResourceManager {
    private final static DBResourceManager INSTANCE = new DBResourceManager();

    private ResourceBundle bundle =
            ResourceBundle.getBundle("properties.db");

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    /**
     * Provides access to the db properties by the String key
     *
     * @param key a key name of db property
     * @return a string db key value
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
