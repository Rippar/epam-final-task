package com.epam.jwd.carrentproject.entity;

import java.util.HashMap;

/**
 * The {@code UserRole} class represents the string constants of user's roles and its integer equivalents in the
 * database.
 */
public class UserRole {

    public static final String ADMIN_ROLE = "admin";
    public static final String CUSTOMER_ROLE = "customer";
    public static final String GUEST_ROLE = "guest";

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
