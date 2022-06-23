package com.epam.jwd.carrentproject.entity;

import java.util.HashMap;

public class UserRole {
    private static HashMap<Integer, String> userRoles = new HashMap<>();

    static {
        userRoles.put(1, "admin");
        userRoles.put(2, "customer");
        userRoles.put(3, "guest");
    }

    public static String getRole(int id) {
        return userRoles.get(id);
    }

    public static void addRole(String roleName) {
        int id = userRoles.size();
        userRoles.put(++id, roleName);
    }
}
