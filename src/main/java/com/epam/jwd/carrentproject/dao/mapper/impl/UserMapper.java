package com.epam.jwd.carrentproject.dao.mapper.impl;

import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.jwd.carrentproject.dao.ColumnName.*;

public class UserMapper implements Mapper<User> {
    private static final UserMapper instance = new UserMapper();

    private UserMapper() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserMapper getInstance() {
        return instance;
    }
    @Override
    public List<User> retrieve(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User.UserBuilder()
                    .withUserId(resultSet.getInt(USER_ID))
                    .withLogin(resultSet.getString(LOGIN))
                    .withPassword(resultSet.getString(PASSWORD))
                    .withFirstName(resultSet.getString(FIRST_NAME))
                    .withLastName(resultSet.getString(LAST_NAME))
                    .withEmail(resultSet.getString(EMAIL))
                    .withPassport(resultSet.getString(PASSPORT_NUMBER))
                    .withIsActive(resultSet.getBoolean(IS_ACTIVE_USER))
                    .withUserRole(resultSet.getInt(ROLE_ID))
                    .build();
            users.add(user);
        }
        return users;
    }
}
