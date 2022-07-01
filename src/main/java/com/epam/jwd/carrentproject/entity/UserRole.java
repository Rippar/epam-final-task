package com.epam.jwd.carrentproject.entity;

import java.util.HashMap;

public class UserRole {

    public static final String ADMIN_ROLE = "админ";
    public static final String CUSTOMER_ROLE = "клиент";
    public static final String GUEST_ROLE = "гость";

    private static HashMap<String, Integer> userRoles = new HashMap<>();

    static {
        userRoles.put(ADMIN_ROLE, 1);
        userRoles.put(CUSTOMER_ROLE, 2);
        userRoles.put(GUEST_ROLE, 3);
    }

    public static int getRoleId(String roleName) {
        return userRoles.get(roleName);
    }

}
